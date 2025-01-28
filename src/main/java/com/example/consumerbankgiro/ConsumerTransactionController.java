package com.example.consumerbankgiro;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bankgiro/consumer/transactions")
public class ConsumerTransactionController {


    final TransactionConsumerService service;

    public ConsumerTransactionController(TransactionConsumerService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<Transaction>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable UUID id) {
        return service.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<?> saveTransaction(@RequestBody  Transaction transaction) {
        service.save(transaction);
        return ResponseEntity.noContent().build();
    }

}
