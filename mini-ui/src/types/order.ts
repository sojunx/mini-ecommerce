export interface Order {
  created_at: string;
  email: string;
  id: string;
  status: string;
  total: number;
  updated_at: string;
}

export interface OrderItem {
  id: number;
  name: string;
  price: number;
  product_id: string;
  quantity: number;
  total: number;
  reviewed: boolean;
}
