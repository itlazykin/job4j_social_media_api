CREATE TABLE users_roles (
    user_id INT REFERENCES users(id),
    role_id INT REFERENCES roles(id)
);