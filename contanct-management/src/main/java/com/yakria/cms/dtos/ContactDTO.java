package com.yakria.cms.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;
@Data
public class ContactDTO {
    @NotBlank(message = "First Name required")
    private String firstName;
    @NotBlank(message = "Last Name required")
    private String lastName;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Contact number is required")
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Contact number must contain exactly 10 digits"
    )
    private String contact;
    private String company;
    private String notes;
    private List<String> tags;
}
