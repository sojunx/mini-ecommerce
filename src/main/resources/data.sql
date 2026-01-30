INSERT INTO users (id, email, name, is_active)
VALUES ('78060e5a-8258-47f2-aa1b-90c5d05e2420', 'test.user@mail.com', 'Test User', false);

INSERT INTO products (id, name, description, price, image, created_at)
VALUES ('b9c6ebbe-a540-415c-b698-fb7b88bd202d', 'test', 'test description', 120.0, '/product.jpg', now()),
       ('fa891053-98a0-4799-ab0b-7addb13065d6', 'test2', 'test2 description', 50.0, '/product.jpg', now());