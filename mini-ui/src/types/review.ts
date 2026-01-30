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
