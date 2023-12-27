package ru.netology.ilyapodoinikov.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor

public class OperationDTO {
    private final int id;
    private final int customerId;
    private int sum;
    private String currency;
    private String merchant;
}