package com.yakria.cms.dtos;

import lombok.Data;

import java.util.List;
@Data
public class ContactDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String contact;
    private String company;
    private String notes;
    private List<String> tags;
}
