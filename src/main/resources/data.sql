INSERT INTO users (id, email, full_name, password, created_at)
VALUES ('78060e5a-8258-47f2-aa1b-90c5d05e2420', 'test.user@mail.com', 'Test User',
        '$2a$10$C9Y3UutpxGAAnowsqmaevu/OrjrqLI5/EFOGpm9dHALFuyQxuakvO', now());

-- Feed users data
INSERT INTO users (id, email, full_name, password, created_at)
SELECT
    gen_random_uuid(),
    'user' || i || '@mail.com',
    'User ' || i,
    '$2a$10$C9Y3UutpxGAAnowsqmaevu/OrjrqLI5/EFOGpm9dHALFuyQxuakvO',
    now()
FROM generate_series(2, 20) i;

INSERT INTO products (id, name, description, price, image, created_at)
VALUES ('b9c6ebbe-a540-415c-b698-fb7b88bd202d', 'product_1', 'random description 1', 69.94, '/product.jpg', now()),
       ('fa891053-98a0-4799-ab0b-7addb13065d6', 'product_2', 'random description 2', 127.35, '/product.jpg', now());

-- Feed products data
INSERT INTO products (id, name, description, price, image, created_at)
SELECT gen_random_uuid(),
       'product_' || i,
       'random description ' || i,
       round((random() * 200 + 10)::numeric, 2),
       '/product.jpg',
       now()
FROM generate_series(3, 50) i;

-- Feed orders data
WITH user_emails AS (
    SELECT id, email
    FROM users
    WHERE email IN (
                    'test.user@mail.com',
                    'user2@mail.com', 'user3@mail.com', 'user4@mail.com', 'user5@mail.com',
                    'user6@mail.com', 'user7@mail.com', 'user8@mail.com', 'user9@mail.com',
                    'user10@mail.com', 'user11@mail.com', 'user12@mail.com', 'user13@mail.com',
                    'user14@mail.com', 'user15@mail.com', 'user16@mail.com', 'user17@mail.com',
                    'user18@mail.com', 'user19@mail.com', 'user20@mail.com'
        )
)
INSERT INTO orders (id, user_id, email, total, status, created_at, updated_at)
SELECT
    gen_random_uuid(),
    id,
    email,
    69.94,
    'COMPLETED',
    now() - (random() * interval '30 days'),
    now() - (random() * interval '30 days')
FROM user_emails;

-- Feed order items data
WITH recent_orders AS (
    SELECT o.id as order_id, ROW_NUMBER() OVER () as rn
    FROM orders o
             JOIN users u ON o.user_id = u.id
    WHERE u.email LIKE 'user%@mail.com' OR u.email = 'test.user@mail.com'
    ORDER BY o.created_at DESC
    LIMIT 20
    )
INSERT INTO order_items (id, product_id, order_id, name, quantity, price, total, is_reviewed)
SELECT
    (SELECT COALESCE(MAX(id), 0) FROM order_items) + rn,
    'b9c6ebbe-a540-415c-b698-fb7b88bd202d'::uuid,
    order_id,
    'product_1',
    1,
    69.94,
    69.94,
    true
FROM recent_orders;

-- Feed reviews data
WITH user_order_data AS (
    SELECT DISTINCT
        u.id as user_id,
        u.email,
        o.id as order_id
    FROM users u
             JOIN orders o ON u.id = o.user_id
    WHERE u.email LIKE 'user%@mail.com' OR u.email = 'test.user@mail.com'
    LIMIT 20
    )
INSERT INTO reviews (id, user_id, product_id, order_id, email, rating, comment, created_at)
SELECT
    gen_random_uuid(),
    user_id,
    'b9c6ebbe-a540-415c-b698-fb7b88bd202d'::uuid,
    order_id,
    email,
    (random() * 4 + 1)::integer, -- rating từ 1-5
    CASE (random() * 5)::integer
        WHEN 0 THEN 'Great product! Highly recommended.'
        WHEN 1 THEN 'Good quality, fast delivery.'
        WHEN 2 THEN 'Satisfied with my purchase.'
        WHEN 3 THEN 'Excellent value for money.'
        WHEN 4 THEN 'Very happy with this product.'
        ELSE 'Amazing quality!'
END,
    now() - (random() * interval '25 days')
FROM user_order_data;