import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Separator } from "@/components/ui/separator";
import http from "@/lib/http";
import type { Order, OrderItem } from "@/types/order";
import { BadgeCheck } from "lucide-react";
import type { FormEvent } from "react";
import { useRevalidator } from "react-router";

const ratingOpts = [
  { value: 1, label: "😐 Poor" },
  { value: 2, label: "😐 Fair" },
  { value: 3, label: "🙂 Good" },
  { value: 4, label: "🙂 Great" },
  { value: 5, label: "😍 Excellent" },
];

interface OrderProductRatingProps {
  order: Order;
  item: OrderItem;
}

const OrderProductRating = ({ order, item }: OrderProductRatingProps) => {
  const { revalidate } = useRevalidator();

  const handleSubmit = async (
    e: FormEvent<HTMLFormElement>,
    item: OrderItem,
  ) => {
    e.preventDefault();

    const formData = new FormData(e.currentTarget);
    const rating = formData.get("rating");
    const comment = formData.get("comment");

    const payload = {
      rating,
      comment,
      product_id: item.product_id,
      order_id: order.id,
    };

    await http.post("/api/reviews", payload);
    revalidate();
  };

  return (
    <form
      key={item.id}
      className="rounded-md border p-3"
      onSubmit={(e) => handleSubmit(e, item)}
    >
      <div className="flex flex-wrap items-center justify-between gap-3">
        <div>
          <div className="font-medium flex items-center gap-1">
            {item.reviewed && (
              <BadgeCheck size={20} className="text-green-700/80" />
            )}
            <p>{item.name}</p>
          </div>
          <p className="text-xs text-muted-foreground">
            Quantity: {item.quantity} · ${item.price.toFixed(2)}
          </p>
        </div>

        <div className="text-right">
          <p className="text-sm text-muted-foreground">Total</p>
          <p className="font-semibold">${item.total.toFixed(2)}</p>
        </div>
      </div>

      {!item.reviewed && (
        <>
          <Separator className="my-3" />

          <div className="flex flex-col gap-2">
            <div className="flex flex-wrap items-center gap-2">
              <p className="text-sm font-medium">Rating</p>
              <div className="flex flex-wrap items-center gap-2">
                {ratingOpts.map((option) => (
                  <label
                    key={option.value}
                    className={`flex cursor-pointer items-center gap-2 rounded-md border px-2 py-1 text-sm transition bg-transparent`}
                  >
                    <input
                      type="radio"
                      name="rating"
                      value={option.value}
                      defaultChecked={option.value === 3}
                      className="h-4 w-4"
                    />
                    <span>{option.label}</span>
                  </label>
                ))}
              </div>
            </div>
            <p className="text-sm font-medium">Comments</p>
            <div className="flex flex-col gap-2 sm:flex-row">
              <Input
                name="comment"
                placeholder="Write a comment for this item..."
              />
              <Button type="submit">Add comment</Button>
            </div>
          </div>
        </>
      )}
    </form>
  );
};

export default OrderProductRating;
