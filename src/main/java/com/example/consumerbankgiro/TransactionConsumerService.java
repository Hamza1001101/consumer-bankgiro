package com.example.consumerbankgiro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TransactionConsumerService {

    final JdbcClient jdbcClient;


    private static final Logger logger = LoggerFactory.getLogger(TransactionConsumerService.class);

    public TransactionConsumerService(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

 //   @KafkaListener(topics = "some-transactions", groupId = "consumer-one")
    public void consumeTransaction(String message) {
        var mapper = new ObjectMapper();
        Transaction transaction;
        try {
            transaction = mapper.readValue(message, Transaction.class);
            logger.info("Received transaction: {}", transaction);

            saveTransaction(transaction);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    //@Scheduled(cron = "*/5 * * * * *")
    public void dummyTextProcessor() {
        Transaction transaction = generateRandomTransaction();
        saveTransaction(transaction);
        logger.info("received transaction {}", transaction);
    }

    public List<Transaction> findAll() {
        var sql = "select * from transactions_v";
        return jdbcClient.sql(sql)
                .query(Transaction.class)
                .list();

    }

    public Optional<Transaction> findById(UUID uuid) {
        var sql = "select * from transactions_v where transaction_id=?";
        return jdbcClient.sql(sql)
                .params("transaction_id", uuid)
                .query(Transaction.class)
                .optional();
    }


    public void saveTransaction(Transaction transaction) {
        var sql = "insert into transactions_v(transaction_id, sender_account, receiver_account, amount) values(?,?,?,?)";
        jdbcClient.sql(sql)
                .params(List.of(
                        UUID.randomUUID(), transaction.senderAccount(), transaction.receiverAccount(), transaction.amount()

                ))
                .update();
        logger.info("successfully saved to database");
    }

    private Transaction generateRandomTransaction() {
        var senderAccount = "ACC" + ThreadLocalRandom.current().nextInt(1000, 9999);
        var receiverAccount = "ACC" + ThreadLocalRandom.current().nextInt(1000, 9999);
        var amount = ThreadLocalRandom.current().nextDouble(10.0, 10000.0);
        return new Transaction(UUID.randomUUID().toString(), senderAccount, receiverAccount, amount);
    }

    public void save(Transaction transaction) {
        saveTransaction(transaction);
    }
}
