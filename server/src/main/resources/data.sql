INSERT INTO users (id, email, password_hash, full_name, role, created_at, updated_at)
VALUES ('e9609483-293e-4bc0-a4de-04d04b5d6ea3',
        'test.user@mail.com',
        '$2a$10$eh4SxgtEs4AgNtpYgXTn1OPpPCWn0z47RlGduHnJ.jZEOCaAZ1MJW',
        'Nguyen Van A',
        'USER',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP);

INSERT INTO carts (id, user_id)
VALUES (1, 'e9609483-293e-4bc0-a4de-04d04b5d6ea3');

INSERT INTO products (id, sku, name, description, price, category, created_at, updated_at)
VALUES ('2e9194bc-2063-4da2-b93f-749d008adcff', 'SKU-001', 'Classic White T-Shirt',
        'A comfortable 100% cotton white t-shirt.', 19.99,
        'CLOTHING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

       (random_uuid(), 'SKU-002', 'Wireless Headphones', 'Noise-canceling over-ear wireless headphones.', 149.50,
        'ELECTRONICS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

       (random_uuid(), 'SKU-003', 'Mechanical Keyboard', 'RGB backlit mechanical keyboard with blue switches.', 89.00,
        'ELECTRONICS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

       (random_uuid(), 'SKU-004', 'Leather Wallet', 'Handcrafted genuine leather wallet in brown.', 45.00,
        'ACCESSORIES', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

       (random_uuid(), 'SKU-005', 'Running Shoes', 'Lightweight and breathable shoes for daily running.', 120.00,
        'SPORTS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);