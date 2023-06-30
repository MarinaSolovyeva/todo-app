package com.todolist.todolistproject.DTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDto {

    private Long id;

    @Size(min = 6, max = 50, message = "Email should be at least 6 characters long and not longer than 50")
    private String email;

    @NotEmpty(message = "Name should not be empty")
    private String userName;

    @Size(min = 6, max = 50, message = "Password should be at least 6 characters long and not longer than 50")
    private String password;

    private String matchingPassword;

}