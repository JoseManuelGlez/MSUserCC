package org.example.msusercc.controller.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ValidateUserResponse {
    private String id;
    private String email;
    private String password;
}
