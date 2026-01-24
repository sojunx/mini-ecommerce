INSERT INTO products (id, name, description, category, stock, price, created_at, updated_at)
VALUES (gen_random_uuid(), 'Test Audio', 'Test Audio Description', 'AUDIO', 10, 100, now(), now()),
       (gen_random_uuid(), 'Test Keyboard', 'Test Keyboard Description', 'KEYBOARD', 10, 100, now(), now()),
       (gen_random_uuid(), 'Test Monitor', 'Test Monitor Description', 'MONITOR', 10, 100, now(), now());