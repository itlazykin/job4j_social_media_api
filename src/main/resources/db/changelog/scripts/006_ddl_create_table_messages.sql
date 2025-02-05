CREATE TABLE messages
(
    id                  SERIAL PRIMARY KEY,
    user_sender_id      INT REFERENCES users(id),
    user_receiver_id    INT REFERENCES users(id),
    content             TEXT
);