-- Generate UUIDs for linking
SET @prod1 = RANDOM_UUID();
SET @prod2 = RANDOM_UUID();
SET @prod3 = RANDOM_UUID();
SET @prod4 = RANDOM_UUID();
SET @prod5 = RANDOM_UUID();
SET @prod6 = RANDOM_UUID();
SET @prod7 = RANDOM_UUID();
SET @prod8 = RANDOM_UUID();
SET @prod9 = RANDOM_UUID();
SET @prod10 = RANDOM_UUID();

-- Products (Using the session variables for ID)
-- Note: CATEGORY must match the names in your ProductCategory Enum (case-sensitive)
INSERT INTO PRODUCTS (ID, NAME, DESCRIPTION, BASE_PRICE, CATEGORY, IMAGE_URL, CREATED_AT, UPDATED_AT)
VALUES (@prod1, 'Classic White T-Shirt', 'Comfortable 100% cotton t-shirt perfect for everyday wear', 19.99, 'CLOTHING', 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab', NOW(), NOW()),
       (@prod2, 'Blue Denim Jeans', 'Stylish slim-fit jeans made from premium denim fabric', 59.99, 'CLOTHING', 'https://images.unsplash.com/photo-1542272604-787c3835535d', NOW(), NOW()),
       (@prod3, 'Wireless Bluetooth Headphones', 'High-quality over-ear headphones with noise cancellation', 149.99, 'ELECTRONICS', 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e', NOW(), NOW()),
       (@prod4, 'Stainless Steel Water Bottle', 'Insulated bottle keeps drinks cold for 24 hours', 24.99, 'ACCESSORIES', 'https://images.unsplash.com/photo-1602143407151-7111542de6e8', NOW(), NOW()),
       (@prod5, 'Leather Laptop Bag', 'Professional messenger bag with padded laptop compartment', 89.99, 'ACCESSORIES', 'https://images.unsplash.com/photo-1553062407-98eeb64c6a62', NOW(), NOW()),
       (@prod6, 'Running Sneakers', 'Lightweight athletic shoes with cushioned sole', 79.99, 'FOOTWEAR', 'https://images.unsplash.com/photo-1542291026-7eec264c27ff', NOW(), NOW()),
       (@prod7, 'Smart Watch', 'Fitness tracker with heart rate monitor and GPS', 199.99, 'ELECTRONICS', 'https://images.unsplash.com/photo-1523275335684-37898b6baf30', NOW(), NOW()),
       (@prod8, 'Yoga Mat', 'Non-slip exercise mat with carrying strap', 34.99, 'SPORTS', 'https://images.unsplash.com/photo-1601925260368-ae2f83cf8b7f', NOW(), NOW()),
       (@prod9, 'Coffee Maker', 'Programmable drip coffee maker with thermal carafe', 69.99, 'HOME_KITCHEN', 'https://images.unsplash.com/photo-1517668808822-9ebb02f2a0e6', NOW(), NOW()),
       (@prod10, 'Sunglasses', 'UV protection polarized sunglasses', 44.99, 'ACCESSORIES', 'https://images.unsplash.com/photo-1572635196237-14b3f281503f', NOW(), NOW());

-- Variants for Classic White T-Shirt (Product ID 1)
INSERT INTO PRODUCT_VARIANTS (PRODUCT_ID, SKU, SIZE, COLOR, PRICE, STOCK_QUANTITY, IMAGE_URL)
VALUES (@prod1, 'TWS-S-WHT', 'S', 'White', 19.99, 50, 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab'),
       (@prod1, 'TWS-M-WHT', 'M', 'White', 19.99, 75, 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab'),
       (@prod1, 'TWS-L-WHT', 'L', 'White', 19.99, 60, 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab'),
       (@prod1, 'TWS-XL-WHT', 'XL', 'White', 19.99, 40, 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab');

-- Variants for Blue Denim Jeans (Product ID 2)
INSERT INTO PRODUCT_VARIANTS (PRODUCT_ID, SKU, SIZE, COLOR, PRICE, STOCK_QUANTITY, IMAGE_URL)
VALUES ( @prod2, 'JNS-30-BLU', '30', 'Blue', 59.99, 30, 'https://images.unsplash.com/photo-1542272604-787c3835535d'),
       ( @prod2, 'JNS-32-BLU', '32', 'Blue', 59.99, 45, 'https://images.unsplash.com/photo-1542272604-787c3835535d'),
       ( @prod2, 'JNS-34-BLU', '34', 'Blue', 59.99, 35, 'https://images.unsplash.com/photo-1542272604-787c3835535d'),
       ( @prod2, 'JNS-36-BLU', '36', 'Blue', 59.99, 25, 'https://images.unsplash.com/photo-1542272604-787c3835535d');

-- Variants for Wireless Headphones (Product ID 3)
INSERT INTO PRODUCT_VARIANTS (PRODUCT_ID, SKU, SIZE, COLOR, PRICE, STOCK_QUANTITY, IMAGE_URL)
VALUES (@prod3, 'HDP-OS-BLK', 'One Size', 'Black', 149.99, 100, 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e'),
       (@prod3, 'HDP-OS-SLV', 'One Size', 'Silver', 149.99, 80, 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e'),
       (@prod3, 'HDP-OS-WHT', 'One Size', 'White', 149.99, 90, 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e');

-- Variants for Running Sneakers (Product ID 6)
INSERT INTO PRODUCT_VARIANTS (PRODUCT_ID, SKU, SIZE, COLOR, PRICE, STOCK_QUANTITY, IMAGE_URL)
VALUES (@prod6, 'SNK-8-BLK', '8', 'Black', 79.99, 40, 'https://images.unsplash.com/photo-1542291026-7eec264c27ff'),
       (@prod6, 'SNK-9-BLK', '9', 'Black', 79.99, 55, 'https://images.unsplash.com/photo-1542291026-7eec264c27ff'),
       (@prod6, 'SNK-10-BLK', '10', 'Black', 79.99, 45, 'https://images.unsplash.com/photo-1542291026-7eec264c27ff'),
       (@prod6, 'SNK-10-RED', '10', 'Red', 79.99, 30, 'https://images.unsplash.com/photo-1542291026-7eec264c27ff');

-- Single variants for products without size/color options
INSERT INTO PRODUCT_VARIANTS (PRODUCT_ID, SKU, SIZE, COLOR, PRICE, STOCK_QUANTITY, IMAGE_URL)
VALUES (@prod4, 'WBT-OS-SLV', 'One Size', 'Silver', 24.99, 150, 'https://images.unsplash.com/photo-1602143407151-7111542de6e8'),
       (@prod5, 'LBG-OS-BRN', 'One Size', 'Brown', 89.99, 60, 'https://images.unsplash.com/photo-1553062407-98eeb64c6a62'),
       (@prod7, 'SMW-OS-BLK', 'One Size', 'Black', 199.99, 75, 'https://images.unsplash.com/photo-1523275335684-37898b6baf30'),
       (@prod8, 'YGM-OS-PUR', 'One Size', 'Purple', 34.99, 120, 'https://images.unsplash.com/photo-1601925260368-ae2f83cf8b7f'),
       (@prod9, 'CFM-OS-BLK', 'One Size', 'Black', 69.99, 50, 'https://images.unsplash.com/photo-1517668808822-9ebb02f2a0e6'),
       (@prod10, 'SNG-OS-BLK', 'One Size', 'Black', 44.99, 95, 'https://images.unsplash.com/photo-1572635196237-14b3f281503f');