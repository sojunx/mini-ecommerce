export interface User {
  id: string;
  email: string;
  first_name: string;
  last_name: string;
  role: string;
}

export interface SignInCommand {
  email: string;
  password: string;
}

export interface Product {
  base_price: number;
  category: string;
  description: string;
  id: string;
  image_url: string;
  name: string;
  variants?: ProductVariant[];
}

export interface ProductVariant {
  color: string;
  image_url: string;
  price: number;
  size: string;
  sku: string;
  stock_quantity: number;
}
