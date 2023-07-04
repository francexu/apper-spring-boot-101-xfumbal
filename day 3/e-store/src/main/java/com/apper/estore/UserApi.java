package com.apper.estore;

import com.apper.estore.payload.CreateUserRequest;
import com.apper.estore.payload.CreateUserResponse;
import com.apper.estore.payload.InvalidUserAgeException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;

@RestController
@RequestMapping("user")
public class UserApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // may @Valid para mag-run yung @NotBlank validation
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest request) throws InvalidUserAgeException {
        LocalDate birthDate = LocalDate.parse(request.getBirthDate());
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();

        if (age < 15) {
            throw new InvalidUserAgeException("Age must not be below 15 years old.");
        } else
        {
            System.out.println(request);
            return new CreateUserResponse("RANDOM_CODE");
        }
    }
}