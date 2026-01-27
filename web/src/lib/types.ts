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
