CREATE TABLE subscriptions
(
    id                  SERIAL PRIMARY KEY,
    user_subscriber_id  INT REFERENCES users(id),
    user_target_id      INT REFERENCES users(id)
);