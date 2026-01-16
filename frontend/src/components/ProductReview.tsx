import { http } from "@/lib/http";
import { Clock, Star, User } from "lucide-react";
import { useEffect, useState } from "react";
import { useParams } from "react-router";

// const RATING_TEXT: Record<number, string> = {
//   1: "Rất tệ",
//   2: "Tệ",
//   3: "Bình thường",
//   4: "Tốt",
//   5: "Rất tuyệt vời",
// };

interface Review {
  id: string;
  rating: number;
  title: string;
  comment: string;
  full_name: string;
  created_at: Date;
  updated_at: Date;
}

const ProductReview = () => {
  const { id } = useParams<{ id: string }>();
  const [reviews, setReviews] = useState<Review[]>([]);
  // const [isModalOpen, setIsModalOpen] = useState(false);
  // const [rating, setRating] = useState<number | null>(null);
  // const [hoverRating, setHoverRating] = useState<number | null>(null);
  // const [comment, setComment] = useState("");
  // const [name, setName] = useState("");

  useEffect(() => {
    const getData = async () => {
      const res = await http.get(`/api/reviews/${id}`);
      const { data } = res.data;

      setReviews(data.reviews);
    };

    getData();
  }, [id]);

  // const handleSubmit = () => {
  //   if (!rating || !comment || !name) return;

  //   const newReview: ReviewDTO = {
  //     id: Date.now().toString(),
  //     rating,
  //     title: hoverRating ? RATING_TEXT[hoverRating] : RATING_TEXT[rating],
  //     comment,
  //     created_at: new Date().toISOString(),
  //     user: {
  //       full_name: name,
  //     },
  //   };

  //   // setReviews((prev) => [newReview, ...prev]);

  //   setRating(0);
  //   setComment("");
  //   setName("");
  //   setIsModalOpen(false);
  // };

  return (
    <div className="max-w-7xl mx-auto px-4 mt-16 border-t">
      <div className="bg-white rounded-xl p-8">
        <h2 className="text-2xl font-semibold text-gray-800 mb-2">Reviews</h2>
        <div className="w-16 h-1 bg-[#ee4d2d] mb-8 rounded" />

        <div className="space-y-6">
          {reviews.map((review) => (
            <div key={review.id} className="border rounded-xl p-5 bg-white">
              <div className="flex items-center gap-1 text-yellow-500">
                {Array.from({ length: 5 }).map((_, i) => (
                  <Star
                    key={i}
                    size={18}
                    className={
                      i < review.rating ? "fill-yellow-500" : "text-gray-300"
                    }
                  />
                ))}
              </div>

              <h4 className="font-semibold text-lg mt-2">{review.title}</h4>

              <p className="text-gray-700 mt-2 leading-relaxed">
                {review.comment}
              </p>

              <div className="flex items-center gap-4 text-sm text-gray-500 mt-3">
                <div className="flex items-center gap-1">
                  <User size={14} />
                  {review.full_name}
                </div>
                <div className="flex items-center gap-1">
                  <Clock size={14} />
                  {new Date(review.created_at).toLocaleDateString()}
                </div>
              </div>

              {/* {review.response && (
                <div className="mt-4 border-l-4 border-[#ee4d2d] bg-gray-50 pl-4 py-3 rounded">
                  <div className="flex items-center gap-2 text-sm font-medium text-gray-700 mb-1">
                    <MessageSquareReply size={16} />
                    Phản hồi từ shop
                  </div>
                  <p className="text-gray-700">
                    {review.response.response_text}
                  </p>
                </div>
              )} */}
            </div>
          ))}
        </div>

        {/* <div className="flex justify-center pt-8">
          <Button
            onClick={() => setIsModalOpen(true)}
            size="xl"
            variant="outline"
            className="gap-2 text-white hover:text-white bg-black hover:bg-gray-800 cursor-pointer"
          >
            <PenLine size={18} />
            Write a Review
          </Button>
        </div>

        <Modal
          open={isModalOpen}
          onCancel={() => setIsModalOpen(false)}
          onOk={handleSubmit}
          okText="Gửi đánh giá"
          cancelText="Hủy"
          title="Viết đánh giá của bạn"
        >
          <div className="flex items-center gap-2">
            {[1, 2, 3, 4, 5].map((star) => {
              const activeRating = hoverRating ?? rating ?? 0;

              return (
                <Star
                  key={star}
                  onClick={() => setRating(star)}
                  onMouseEnter={() => setHoverRating(star)}
                  onMouseLeave={() => setHoverRating(null)}
                  className={`h-6 w-6 cursor-pointer transition ${
                    star <= activeRating
                      ? "fill-yellow-500 text-yellow-500"
                      : "text-gray-300"
                  }`}
                />
              );
            })}

            {(hoverRating ?? rating) && (
              <span className="ml-2 text-sm font-medium text-gray-700">
                {RATING_TEXT[(hoverRating ?? rating) as number]}
              </span>
            )}
          </div>

          <textarea
            value={comment}
            onChange={(e) => setComment(e.target.value)}
            rows={4}
            className="w-full border rounded-lg px-4 py-3 mb-4 mt-6"
            placeholder="Nhận xét của bạn"
          />

          <input
            value={name}
            onChange={(e) => setName(e.target.value)}
            className="w-full border rounded-lg px-4 py-3 mb-6"
            placeholder="Tên của bạn"
          />
        </Modal> */}
      </div>
    </div>
  );
};

export default ProductReview;
