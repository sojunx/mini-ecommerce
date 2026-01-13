-- Inserting products with UUIDs for testing
-- Ensure 'category' matches your ProductCategory enum names (e.g., ELECTRONICS, CLOTHING)

INSERT INTO products (id, sku, name, description, price, category, created_at, updated_at)
VALUES (random_uuid(), 'SKU-001', 'Classic White T-Shirt', 'A comfortable 100% cotton white t-shirt.', 19.99,
        'CLOTHING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

       (random_uuid(), 'SKU-002', 'Wireless Headphones', 'Noise-canceling over-ear wireless headphones.', 149.50,
        'ELECTRONICS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

       (random_uuid(), 'SKU-003', 'Mechanical Keyboard', 'RGB backlit mechanical keyboard with blue switches.', 89.00,
        'ELECTRONICS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

       (random_uuid(), 'SKU-004', 'Leather Wallet', 'Handcrafted genuine leather wallet in brown.', 45.00,
        'ACCESSORIES', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

       (random_uuid(), 'SKU-005', 'Running Shoes', 'Lightweight and breathable shoes for daily running.', 120.00,
        'SPORTS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);