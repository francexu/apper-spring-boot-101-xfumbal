package com.gcash.service;

import lombok.Data; // eliminates boilerplate in this case the getter setters

@Data
public class Account {
    private String id;
    private Double balance;
}
