INSERT INTO products (id, name, description, price, image, created_at)
VALUES ('b9c6ebbe-a540-415c-b698-fb7b88bd202d', 'test', 'test description', 120.0, '/product.jpg', now()),
       ('fa891053-98a0-4799-ab0b-7addb13065d6', 'test2', 'test2 description', 50.0, '/product.jpg', now());

-- Insert 20 orders
INSERT INTO orders (id, email, total, status, created_at, updated_at)
VALUES
    ('a1b2c3d4-1111-1111-1111-111111111111', 'nguyen.van.a@gmail.com', 120.0, 'COMPLETED', now() - interval '10 days', now() - interval '10 days'),
    ('a1b2c3d4-2222-2222-2222-222222222222', 'tran.thi.b@gmail.com', 50.0, 'COMPLETED', now() - interval '9 days', now() - interval '9 days'),
    ('a1b2c3d4-3333-3333-3333-333333333333', 'le.van.c@gmail.com', 120.0, 'COMPLETED', now() - interval '8 days', now() - interval '8 days'),
    ('a1b2c3d4-4444-4444-4444-444444444444', 'pham.thi.d@gmail.com', 50.0, 'COMPLETED', now() - interval '7 days', now() - interval '7 days'),
    ('a1b2c3d4-5555-5555-5555-555555555555', 'hoang.van.e@gmail.com', 120.0, 'COMPLETED', now() - interval '6 days', now() - interval '6 days'),
    ('a1b2c3d4-6666-6666-6666-666666666666', 'vu.thi.f@gmail.com', 50.0, 'COMPLETED', now() - interval '5 days', now() - interval '5 days'),
    ('a1b2c3d4-7777-7777-7777-777777777777', 'dang.van.g@gmail.com', 120.0, 'COMPLETED', now() - interval '4 days', now() - interval '4 days'),
    ('a1b2c3d4-8888-8888-8888-888888888888', 'ngo.thi.h@gmail.com', 50.0, 'COMPLETED', now() - interval '3 days', now() - interval '3 days'),
    ('a1b2c3d4-9999-9999-9999-999999999999', 'bui.van.i@gmail.com', 120.0, 'COMPLETED', now() - interval '2 days', now() - interval '2 days'),
    ('a1b2c3d4-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'do.thi.j@gmail.com', 50.0, 'COMPLETED', now() - interval '1 day', now() - interval '1 day'),
    ('a1b2c3d4-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'phan.van.k@gmail.com', 120.0, 'COMPLETED', now() - interval '15 days', now() - interval '15 days'),
    ('a1b2c3d4-cccc-cccc-cccc-cccccccccccc', 'vo.thi.l@gmail.com', 50.0, 'COMPLETED', now() - interval '14 days', now() - interval '14 days'),
    ('a1b2c3d4-dddd-dddd-dddd-dddddddddddd', 'truong.van.m@gmail.com', 120.0, 'COMPLETED', now() - interval '13 days', now() - interval '13 days'),
    ('a1b2c3d4-eeee-eeee-eeee-eeeeeeeeeeee', 'ly.thi.n@gmail.com', 50.0, 'COMPLETED', now() - interval '12 days', now() - interval '12 days'),
    ('a1b2c3d4-ffff-ffff-ffff-ffffffffffff', 'duong.van.o@gmail.com', 120.0, 'COMPLETED', now() - interval '11 days', now() - interval '11 days'),
    ('a1b2c3d4-0000-1111-2222-333333333333', 'mai.thi.p@gmail.com', 50.0, 'COMPLETED', now() - interval '20 days', now() - interval '20 days'),
    ('a1b2c3d4-1111-2222-3333-444444444444', 'trinh.van.q@gmail.com', 120.0, 'COMPLETED', now() - interval '19 days', now() - interval '19 days'),
    ('a1b2c3d4-2222-3333-4444-555555555555', 'dinh.thi.r@gmail.com', 50.0, 'COMPLETED', now() - interval '18 days', now() - interval '18 days'),
    ('a1b2c3d4-3333-4444-5555-666666666666', 'ha.van.s@gmail.com', 120.0, 'COMPLETED', now() - interval '17 days', now() - interval '17 days'),
    ('a1b2c3d4-4444-5555-6666-777777777777', 'cao.thi.t@gmail.com', 50.0, 'COMPLETED', now() - interval '16 days', now() - interval '16 days');

-- Insert 20 reviews (10 cho mỗi product) với đánh giá từ 1-5 sao
INSERT INTO reviews (id, product_id, order_id, email, rating, comment, created_at)
VALUES
    -- Reviews cho product 'test' (id: b9c6ebbe-a540-415c-b698-fb7b88bd202d)
    (gen_random_uuid(), 'b9c6ebbe-a540-415c-b698-fb7b88bd202d', 'a1b2c3d4-1111-1111-1111-111111111111', 'nguyen.van.a@gmail.com', 5, 'Sản phẩm rất tốt, chất lượng vượt mong đợi. Giao hàng nhanh chóng!', now() - interval '10 days'),
    (gen_random_uuid(), 'b9c6ebbe-a540-415c-b698-fb7b88bd202d', 'a1b2c3d4-3333-3333-3333-333333333333', 'le.van.c@gmail.com', 4, 'Đóng gói cẩn thận, sản phẩm đúng như mô tả. Giá hơi cao nhưng chất lượng xứng đáng.', now() - interval '8 days'),
    (gen_random_uuid(), 'b9c6ebbe-a540-415c-b698-fb7b88bd202d', 'a1b2c3d4-5555-5555-5555-555555555555', 'hoang.van.e@gmail.com', 5, 'Tuyệt vời! Mình đã dùng 1 tuần rồi, rất hài lòng. Sẽ giới thiệu cho bạn bè.', now() - interval '6 days'),
    (gen_random_uuid(), 'b9c6ebbe-a540-415c-b698-fb7b88bd202d', 'a1b2c3d4-7777-7777-7777-777777777777', 'dang.van.g@gmail.com', 2, 'Sản phẩm không đúng như quảng cáo. Chất liệu kém hơn mong đợi, hơi thất vọng.', now() - interval '4 days'),
    (gen_random_uuid(), 'b9c6ebbe-a540-415c-b698-fb7b88bd202d', 'a1b2c3d4-9999-9999-9999-999999999999', 'bui.van.i@gmail.com', 4, 'Sản phẩm tốt, ship hơi lâu nhưng vẫn ok. Chất lượng đúng như quảng cáo.', now() - interval '2 days'),
    (gen_random_uuid(), 'b9c6ebbe-a540-415c-b698-fb7b88bd202d', 'a1b2c3d4-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'phan.van.k@gmail.com', 1, 'Rất tệ! Sản phẩm bị lỗi ngay từ đầu. Liên hệ shop mãi không được hỗ trợ. Không recommend!', now() - interval '15 days'),
    (gen_random_uuid(), 'b9c6ebbe-a540-415c-b698-fb7b88bd202d', 'a1b2c3d4-dddd-dddd-dddd-dddddddddddd', 'truong.van.m@gmail.com', 5, 'Cực kỳ hài lòng! Sản phẩm chính hãng, đóng gói chắc chắn.', now() - interval '13 days'),
    (gen_random_uuid(), 'b9c6ebbe-a540-415c-b698-fb7b88bd202d', 'a1b2c3d4-ffff-ffff-ffff-ffffffffffff', 'duong.van.o@gmail.com', 3, 'Bình thường thôi. Giá hơi cao so với chất lượng. Có thể cân nhắc trước khi mua.', now() - interval '11 days'),
    (gen_random_uuid(), 'b9c6ebbe-a540-415c-b698-fb7b88bd202d', 'a1b2c3d4-1111-2222-3333-444444444444', 'trinh.van.q@gmail.com', 5, 'Rất đáng tiền! Chất lượng xứng đáng với giá thành. Recommend!', now() - interval '19 days'),
    (gen_random_uuid(), 'b9c6ebbe-a540-415c-b698-fb7b88bd202d', 'a1b2c3d4-3333-4444-5555-666666666666', 'ha.van.s@gmail.com', 4, 'Tốt, nhưng màu sắc hơi khác so với hình. Chất lượng ổn.', now() - interval '17 days'),


    -- Reviews cho product 'test2' (id: fa891053-98a0-4799-ab0b-7addb13065d6)
    (gen_random_uuid(), 'fa891053-98a0-4799-ab0b-7addb13065d6', 'a1b2c3d4-2222-2222-2222-222222222222', 'tran.thi.b@gmail.com', 5, 'Giá rẻ mà chất lượng tốt, không ngờ! Rất đáng mua.', now() - interval '9 days'),
    (gen_random_uuid(), 'fa891053-98a0-4799-ab0b-7addb13065d6', 'a1b2c3d4-4444-4444-4444-444444444444', 'pham.thi.d@gmail.com', 4, 'Tốt trong tầm giá. Sản phẩm nhỏ gọn, tiện dụng.', now() - interval '7 days'),
    (gen_random_uuid(), 'fa891053-98a0-4799-ab0b-7addb13065d6', 'a1b2c3d4-6666-6666-6666-666666666666', 'vu.thi.f@gmail.com', 3, 'Tạm được. Không xuất sắc nhưng cũng không tệ. Giá rẻ nên chấp nhận được.', now() - interval '5 days'),
    (gen_random_uuid(), 'fa891053-98a0-4799-ab0b-7addb13065d6', 'a1b2c3d4-8888-8888-8888-888888888888', 'ngo.thi.h@gmail.com', 5, 'Tuyệt vời với mức giá này! Chất lượng vượt trội so với giá tiền.', now() - interval '3 days'),
    (gen_random_uuid(), 'fa891053-98a0-4799-ab0b-7addb13065d6', 'a1b2c3d4-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'do.thi.j@gmail.com', 2, 'Hơi thất vọng. Sản phẩm dùng được nhưng kém bền. Sau vài ngày đã xuất hiện vấn đề.', now() - interval '1 day'),
    (gen_random_uuid(), 'fa891053-98a0-4799-ab0b-7addb13065d6', 'a1b2c3d4-cccc-cccc-cccc-cccccccccccc', 'vo.thi.l@gmail.com', 5, 'Chất lượng tốt, giá cả phải chăng. Sẽ ủng hộ shop lâu dài.', now() - interval '14 days'),
    (gen_random_uuid(), 'fa891053-98a0-4799-ab0b-7addb13065d6', 'a1b2c3d4-eeee-eeee-eeee-eeeeeeeeeeee', 'ly.thi.n@gmail.com', 1, 'Rất tệ! Sản phẩm không hoạt động. Yêu cầu đổi trả nhưng shop không phản hồi.', now() - interval '12 days'),
    (gen_random_uuid(), 'fa891053-98a0-4799-ab0b-7addb13065d6', 'a1b2c3d4-0000-1111-2222-333333333333', 'mai.thi.p@gmail.com', 4, 'Tốt, đúng với giá tiền. Đóng gói cẩn thận, giao hàng đúng hẹn.', now() - interval '20 days'),
    (gen_random_uuid(), 'fa891053-98a0-4799-ab0b-7addb13065d6', 'a1b2c3d4-2222-3333-4444-555555555555', 'dinh.thi.r@gmail.com', 3, 'Bình thường. Không có gì đặc biệt. Dùng tạm được.', now() - interval '18 days'),
    (gen_random_uuid(), 'fa891053-98a0-4799-ab0b-7addb13065d6', 'a1b2c3d4-4444-5555-6666-777777777777', 'cao.thi.t@gmail.com', 5, 'Chất lượng tốt, giá cả hợp lý. Rất đáng để thử!', now() - interval '16 days');
