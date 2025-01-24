package com.example.consumerbankgiro;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "transactions_v")
public record Transaction(@Id String transactionId, String senderAccount, String receiverAccount, double amount) {
}
