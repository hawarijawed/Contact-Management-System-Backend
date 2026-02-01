package com.yakria.cms.dtos;

import lombok.Data;

import java.util.List;

@Data
public class SearchNameDTO {
    private String firstName;
    private String lastName;
    private String email;
    private List<String> tags;
    private String company;
}
