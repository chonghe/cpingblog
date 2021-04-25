package com.cping.cpingblog.common.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginDto implements Serializable {
    @NotBlank(message = "not empty required")
    private String username;

    @NotBlank(message = "password不能为空")
    private String password;
}
