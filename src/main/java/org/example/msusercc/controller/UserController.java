package org.example.msusercc.controller;

import org.example.msusercc.controller.requests.CreateUserRequest;
import org.example.msusercc.controller.requests.ValidateUserRequest;
import org.example.msusercc.controller.responses.BaseResponse;
import org.example.msusercc.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService service;

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable String id) {
        BaseResponse baseResponse = service.get(id);

        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateUserRequest request) {
        BaseResponse baseResponse = service.create(request);

        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping("login")
    public ResponseEntity<BaseResponse> validate(@RequestBody ValidateUserRequest request){
        BaseResponse baseResponse = service.login(request);

        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }
}
