package com.example.apptransaction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class DatosDto {
    private String transaction_id;
    private Integer user_id;
    private String date;
    private String description;
    private Double amount;

}
