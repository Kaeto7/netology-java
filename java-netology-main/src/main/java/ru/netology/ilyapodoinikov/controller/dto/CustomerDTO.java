package ru.netology.ilyapodoinikov.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private int id;
    private String name;
}
