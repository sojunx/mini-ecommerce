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
  category: string;
  created_at: string;
  description: string;
  id: string;
  name: string;
  price: number;
  sku: string;
  updated_at: string;
}
