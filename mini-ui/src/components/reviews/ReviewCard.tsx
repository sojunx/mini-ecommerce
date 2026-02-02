import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import useData from "@/hooks/useData";
import { formatDate } from "@/lib/utils";
import type { Product } from "@/types/product";
import type { Review } from "@/types/review";
import { Link } from "react-router";

const ReviewCard = ({ review }: { review: Review }) => {
  const { data } = useData<Product>(`/api/products/${review.product_id}`);

  return (
    <div
      key={review.id}
      className="rounded-2xl border bg-background p-5 space-y-3 w-md max-md:w-full"
    >
      <div className="flex items-start justify-between gap-4">
        <div>
          <Link
            to={`/product/${data?.id}`}
            className="text-lg font-medium hover:underline"
          >
            {data?.name}
          </Link>

          <p className="text-sm text-muted-foreground">
            {formatDate(review.created_at)}
          </p>
        </div>
        <Badge variant="secondary">{review.rating}★</Badge>
      </div>
      <p className="text-sm text-muted-foreground">{review.comment}</p>
      <div className="flex gap-2">
        <Button size="sm" variant="outline" asChild>
          <Link to={`/reviews/edit/${review.id}`}>Edit</Link>
        </Button>
        <Button size="sm" variant="ghost" asChild>
          <Link to={`/reviews/delete/${review.id}`}>Delete</Link>
        </Button>
      </div>
    </div>
  );
};

export default ReviewCard;
