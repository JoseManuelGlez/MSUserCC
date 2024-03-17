package org.example.msusercc.service.interfaces;

import org.example.msusercc.controller.responses.BaseResponse;
import org.example.msusercc.entity.User;

public interface IUserService {
    BaseResponse get(String request);

    User findOneAndEnsureExist(String id);
}
