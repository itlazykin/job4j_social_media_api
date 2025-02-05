CREATE TABLE posts
(
    id              SERIAL PRIMARY KEY,
    title           VARCHAR NOT NULL,
    description     TEXT,
    user_id         INT REFERENCES posts(id)
);