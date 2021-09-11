
CREATE TABLE IF NOT EXISTS QUOTE (
    id SERIAL PRIMARY KEY,
    symbol VARCHAR(255),
    open_value double,
    close_value double,
    timestamp TIMESTAMP
);