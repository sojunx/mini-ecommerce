import { Button } from "@/components/ui/button";
import { Label } from "@/components/ui/label";
import { Textarea } from "@/components/ui/textarea";
import http from "@/lib/http";
import type { Order, OrderItem } from "@/lib/types";

const RatingForm = ({ item, order }: { item: OrderItem; order: Order }) => {
  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const formData = new FormData(e.currentTarget);

    const values = Object.fromEntries(formData.entries());
    const data = { ...values, order_id: order.id, product_id: item.product_id };

    console.log(data);

    try {
      const res = await http.post("/api/reviews", data);

      console.log(res);
      window.location.reload();
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <form className="grid gap-4" onSubmit={handleSubmit}>
      <div className="grid gap-2">
        <Label>Rating</Label>
        <div className="flex flex-wrap gap-2">
          {[1, 2, 3, 4, 5].map((rating) => (
            <label
              key={rating}
              className="group flex cursor-pointer items-center gap-2 rounded-md border border-border px-3 py-2 text-xs font-medium transition hover:border-primary"
            >
              <input
                type="radio"
                name={`rating`}
                id={`rating-${item.id}-${rating}`}
                value={rating}
                defaultChecked={rating === 5}
                className="peer sr-only"
              />
              <span className="text-amber-500">★</span>
              <span className="text-foreground">{rating}</span>
            </label>
          ))}
        </div>
      </div>

      <div className="grid gap-2">
        <Label htmlFor={`comment-${item.id}`}>Comment</Label>
        <Textarea
          id={`comment-${item.id}`}
          name={`comment`}
          placeholder="Tell us what you liked (or didn’t) about this product."
        />
      </div>

      <Button type="submit">Submit</Button>
    </form>
  );
};

export default RatingForm;
