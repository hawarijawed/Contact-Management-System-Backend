package com.yakria.cms.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UpdateContactDTO {
    private String name;
    private String email;
    private String contact;
    private String notes;
    private String company;
    private List<String> tags;
}
