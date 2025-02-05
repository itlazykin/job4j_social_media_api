CREATE TABLE friends
(
    id              SERIAL PRIMARY KEY,
    status          BOOLEAN,
    requester_id    INT REFERENCES users(id),
    receiver_id     INT REFERENCES users(id)
);