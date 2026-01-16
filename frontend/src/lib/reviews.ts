export interface ReviewDTO {
  id: string;
  rating: number;
  title: string;
  comment: string;
  created_at: string;

  user: {
    id?: string;
    full_name: string;
  };

  response?: {
    id: number;
    response_text: string;
    admin: {
      id: string;
      full_name: string;
    };
    created_at: string;
  };
}

export const mockReviews: ReviewDTO[] = [
  {
    id: "1",
    rating: 5,
    title: "Rất tuyệt vời",
    comment: "Sản phẩm đúng mô tả, giao hàng nhanh.",
    created_at: "2025-01-10T09:12:00Z",
    user: {
      id: "user-uuid-1",
      full_name: "Nguyễn Văn A",
    },
    response: {
      id: 101,
      response_text: "Cảm ơn bạn đã tin tưởng và ủng hộ shop ❤️",
      created_at: "2025-01-10T12:00:00Z",
      admin: {
        id: "admin-uuid-1",
        full_name: "Shop Admin",
      },
    },
  },
  {
    id: "2",
    rating: 4,
    title: "Tốt",
    comment: "Giá hợp lý, đóng gói tốt.",
    created_at: "2025-01-12T15:30:00Z",
    user: {
      id: "user-uuid-2",
      full_name: "Trần Thị B",
    },
  },
  {
    id: "3",
    rating: 4,
    title: "Tốt",
    comment: "Giá hợp lý, đóng gói tốt.",
    created_at: "2025-01-12T15:30:00Z",
    user: {
      id: "user-uuid-2",
      full_name: "Trần Thị B",
    },
  },
];
