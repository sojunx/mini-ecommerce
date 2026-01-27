import type { Review } from "@/lib/types";

const Review = ({ review }: { review: Review }) => {
  return (
    <div>
      {review.rating} Stars
      <p>{review.comment}</p>
      <p>{review.email}</p>
      <p>{review.created_at}</p>
    </div>
  );
};

export default Review;
