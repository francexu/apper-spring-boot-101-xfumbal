package com.apper.theblogservice.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateBloggerRequest {
    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Password is required.")
    @Size(min = 5, message = "Password must be at least 5 characters.")
    private String password;
}
