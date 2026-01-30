import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Textarea } from "@/components/ui/textarea";
import type { Order, OrderItem } from "@/lib/types";
import { formatCurrency, formatDate } from "@/lib/utils";
import { useLoaderData } from "react-router";

interface OrderPageLoaderData {
  order: Order;
  items: OrderItem[];
}

const OrderPage = () => {
  const { order, items } = useLoaderData<OrderPageLoaderData>();
  const userId =
    typeof window !== "undefined" ? localStorage.getItem("user_id") : null;

  const order_num = order.id.slice(0, 8).toUpperCase();

  return (
    <div className="mx-auto w-full max-w-6xl px-4 py-10">
      <div className="flex flex-col gap-8 lg:flex-row lg:items-start">
        <div className="flex-1 space-y-6">
          {/* Header */}
          <div className="space-y-2">
            <h1 className="text-2xl font-semibold">ORDER #{order_num}</h1>

            <div className="text-sm text-muted-foreground space-y-2">
              <p>Total: {formatCurrency(order.total)}</p>
              <p>Customer email: {order.email}</p>
              <p>
                Placed on {formatDate(order.created_at)} · {items.length} items
              </p>
            </div>
          </div>

          {!userId && (
            <Card>
              <CardHeader>
                <CardTitle>Reviewer contact</CardTitle>
                <CardDescription>
                  Provide one email address for all reviews in this order.
                </CardDescription>
              </CardHeader>
              <CardContent className="grid gap-2">
                <Label htmlFor="reviewer-email">Email</Label>
                <Input
                  id="reviewer-email"
                  type="email"
                  placeholder="you@example.com"
                  autoComplete="email"
                />
              </CardContent>
            </Card>
          )}

          {items.length === 0 ? (
            <Card>
              <CardHeader>
                <CardTitle>No items found</CardTitle>
                <CardDescription>
                  This order does not contain any items to review.
                </CardDescription>
              </CardHeader>
            </Card>
          ) : (
            items.map((item) => (
              <Card key={item.id}>
                <CardHeader>
                  <div className="flex flex-wrap items-start justify-between gap-4">
                    <div className="flex items-center gap-4">
                      <div className="size-16 rounded-lg overflow-hidden bg-muted border border-border">
                        <img
                          src={"/product.jpg"}
                          alt={item.name}
                          className="size-16 object-cover"
                          draggable={false}
                        />
                      </div>
                      <div>
                        <CardTitle className="text-lg">{item.name}</CardTitle>
                        <p className="text-xs text-muted-foreground">
                          Quantity: {item.quantity}
                        </p>
                      </div>
                    </div>
                    <Badge variant="outline">
                      {formatCurrency(item.price * item.quantity)}
                    </Badge>
                  </div>
                </CardHeader>
                <CardContent>
                  <form className="grid gap-4">
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
                              name={`rating-${item.id}`}
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
                        placeholder="Tell us what you liked (or didn’t) about this product."
                      />
                    </div>

                    <Button type="submit">Submit</Button>
                  </form>
                </CardContent>
              </Card>
            ))
          )}
        </div>
      </div>
    </div>
  );
};

export default OrderPage;
