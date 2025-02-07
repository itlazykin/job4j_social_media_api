CREATE TABLE feeds
(
    id              SERIAL PRIMARY KEY,
    user_id         INT REFERENCES users(id),
    post_id         INT REFERENCES posts(id),
    create_post     TIMESTAMP
);