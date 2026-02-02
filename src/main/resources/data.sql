INSERT INTO users (id, email, full_name, password, created_at)
VALUES ('78060e5a-8258-47f2-aa1b-90c5d05e2420', 'test.user@mail.com', 'Test User',
        '$2a$10$C9Y3UutpxGAAnowsqmaevu/OrjrqLI5/EFOGpm9dHALFuyQxuakvO', now());

INSERT INTO products (id, name, description, price, image, created_at)
VALUES ('b9c6ebbe-a540-415c-b698-fb7b88bd202d', 'product_1', 'random description 1', 69.94, '/product.jpg', now()),
       ('fa891053-98a0-4799-ab0b-7addb13065d6', 'product_2', 'random description 2', 127.35, '/product.jpg', now());

INSERT INTO products (id, name, description, price, image, created_at)
SELECT gen_random_uuid(),
       'product_' || i,
       'random description ' || i,
       round((random() * 200 + 10)::numeric, 2),
       '/product.jpg',
       now()
FROM generate_series(3, 20) i;
