package com.yakria.cms.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class UpdateContactDTO {
    private String firstName;
    private String lastName;
    @Email(message = "Valid email required")
    private String email;
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Contact number must contain exactly 10 digits"
    )
    private String contact;
    private String notes;
    private String company;
    private List<String> tags;
}
