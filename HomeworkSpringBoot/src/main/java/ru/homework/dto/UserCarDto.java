package ru.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCarDto {
    private String firstName;
    private String lastName;
    private String model;
}
