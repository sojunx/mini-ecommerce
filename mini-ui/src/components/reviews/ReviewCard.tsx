import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";

const ReviewCard = ({ review }: { review: any }) => {
  return (
    <div
      key={review.product}
      className="rounded-2xl border bg-background p-5 space-y-3"
    >
      <div className="flex items-start justify-between gap-4">
        <div>
          <h3 className="text-lg font-medium">{review.product}</h3>
          <p className="text-sm text-muted-foreground">{review.date}</p>
        </div>
        <Badge variant="secondary">{review.rating}★</Badge>
      </div>
      <p className="text-sm text-muted-foreground">{review.comment}</p>
      <div className="flex gap-2">
        <Button size="sm" variant="outline">
          Edit
        </Button>
        <Button size="sm" variant="ghost">
          Delete
        </Button>
      </div>
    </div>
  );
};

export default ReviewCard;
