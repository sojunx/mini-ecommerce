INSERT INTO users (id, email, password, first_name, last_name, role, created_at)
VALUES (gen_random_uuid(), 'guest@mail.com', '$2a$10$zwn4cJurY3gfKBisEhoZVe65DKE3ETeMOfoC',
        'Test', 'User', 'USER', now());

INSERT INTO products (id, name, description, category, stock, price, created_at, updated_at)
VALUES ('74e586e9-3f61-490b-b70e-0fc986b1ebc0', 'Test Audio', 'Test Audio Description',
        'AUDIO', 10, 100, now(), now()),

       ('3a5b2eb7-d3d3-4282-8073-7a91f908e14a', 'Test Keyboard', 'Test Keyboard Description',
        'KEYBOARD', 1, 100, now(), now()),

       (gen_random_uuid(), 'Test Monitor', 'Test Monitor Description',
        'MONITOR', 0, 100, now(), now());