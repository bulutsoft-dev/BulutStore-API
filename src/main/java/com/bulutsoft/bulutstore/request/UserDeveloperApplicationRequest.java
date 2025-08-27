package com.bulutsoft.bulutstore.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class UserDeveloperApplicationRequest {
    @NotBlank
    private String applicationText;
}

