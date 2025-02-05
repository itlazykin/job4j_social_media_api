CREATE TABLE files
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR NOT NULL,
    path        VARCHAR UNIQUE NOT NULL,
    post_id     INT REFERENCES posts(id)
);