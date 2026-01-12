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

export interface ProductVariant {
  sku: string;
  size: string;
  color: string;
  price: number;
  stock_quantity: number;
  image_url: string;
}

export interface Product {
  id: string;
  name: string;
  description: string;
  base_price: number;
  category: string;
  image_url: string;
  variants: ProductVariant[];
}
