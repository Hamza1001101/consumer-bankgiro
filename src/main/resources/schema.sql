CREATE TABLE IF NOT EXISTS transactions_v (
                                transaction_id VARCHAR(255) PRIMARY KEY,
                                sender_account VARCHAR(255) NOT NULL,
                                receiver_account VARCHAR(255) NOT NULL,
                                amount DOUBLE PRECISION NOT NULL
);
