package com.apper.estore;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.") // validation kung email siya
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, message = "Password must be at least 8 characters.") // minimum number of characters
    private String password;

    @JsonProperty("first_name") // para mag-align siya sa raw body sa postman
    @NotBlank(message = "First name is required.") // validation kung empty or null
    private String firstName;

    @JsonProperty("middle_name")
    private String middleName;

    @JsonProperty("last_name")
    @NotBlank(message = "Last name is required.")
    private String lastName;

    @JsonProperty("birth_date")
    @NotBlank(message = "Birthdate is required.")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "Invalid format.") // for date format, search for other ways
    private String birthDate;
}
