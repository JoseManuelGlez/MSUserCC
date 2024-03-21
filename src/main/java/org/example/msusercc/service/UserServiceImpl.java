package org.example.msusercc.service;

import org.example.msusercc.controller.requests.CreateUserRequest;
import org.example.msusercc.controller.requests.ValidateUserRequest;
import org.example.msusercc.controller.responses.BaseResponse;
import org.example.msusercc.controller.responses.GetUserResponse;
import org.example.msusercc.controller.responses.ValidateUserResponse;
import org.example.msusercc.entity.User;
import org.example.msusercc.entity.projections.IUserProjection;
import org.example.msusercc.repository.IUserRepository;
import org.example.msusercc.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository repository;

    @Override
    public BaseResponse get(String id) {

        User user = findOneAndEnsureExist(id);

        return BaseResponse.builder()
                .data(from(user))
                .message("User found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public User findOneAndEnsureExist(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("The user does not exist"));
    }

    @Override
    public BaseResponse create(CreateUserRequest request) {
        User user = from(request);

        return BaseResponse.builder()
                .data(from(repository.save(user)))
                .message("User created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse login(ValidateUserRequest request) {
        IUserProjection user = repository.findUserByEmailAndPassword(request.getEmail(), request.getPassword());
        System.out.println("aaaaaaa");
        System.out.println(user);
        return BaseResponse.builder()
                .data(from(user))
                .message("User found correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    private GetUserResponse from(User user){
        GetUserResponse response = new GetUserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());

        return response;
    }

    private User from(CreateUserRequest request){
        User response = new User();

        response.setName(request.getName());
        response.setPassword(request.getPassword());
        response.setEmail(request.getEmail());

        return response;
    }

    private ValidateUserResponse from(IUserProjection projection){
        ValidateUserResponse response = new ValidateUserResponse();

        response.setId(projection.getUserId());
        response.setPassword(projection.getUserPassword());
        response.setEmail(projection.getUserEmail());

        return response;
    }
}
