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
  price: number;
  product_id: string;
  quantity: number;
  total: number;
}

export interface Product {
  id: string;
  name: string;
  description: string;
  price: number;
  image: string;
}

export interface Review {
  id: string;
  email: string;
  comment: string;
  rating: number;
  created_at: string;
}

export interface ReviewStats {
  average_rating: number;
  total: number;
  ratings_count: {
    rating: number;
    count: number;
  }[];
}

export interface ProductDetails {
  product: Product;
  reviews: Review[];
  review_stats: ReviewStats;
}

export interface User {
  id: string;
  email: string;
  name: string;
}
