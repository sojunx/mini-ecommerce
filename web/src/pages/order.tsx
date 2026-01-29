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
import { Separator } from "@/components/ui/separator";
import { Textarea } from "@/components/ui/textarea";
import type { Order, OrderItem } from "@/lib/types";
import { formatDate } from "@/lib/utils";
import { useLoaderData } from "react-router";

interface OrderPageLoaderData {
  order: Order;
  items: OrderItem[];
}

const OrderPage = () => {
  const { order, items } = useLoaderData<OrderPageLoaderData>();
  const itemsTotal = items.reduce((sum, item) => sum + item.total, 0);
  const userId =
    typeof window !== "undefined" ? localStorage.getItem("user_id") : null;
  const reviewerLabel = userId ? "App user" : "Anonymous";
  const formatCurrency = (value: number) =>
    new Intl.NumberFormat("en-US", {
      style: "currency",
      currency: "USD",
    }).format(value);

  return (
    <div className="mx-auto w-full max-w-6xl px-4 py-10">
      <div className="flex flex-col gap-2">
        <div className="flex flex-wrap items-center gap-3">
          <h1 className="text-2xl font-semibold text-foreground">
            Order {order.id}
          </h1>
          <Badge variant="secondary" className="capitalize">
            {order.status}
          </Badge>
        </div>
        <p className="text-sm text-muted-foreground">
          Placed {formatDate(order.created_at)} · {items.length} items
        </p>
      </div>

      <Separator className="my-6" />

      <div className="grid gap-6 lg:grid-cols-[2fr_1fr]">
        <div className="flex flex-col gap-6">
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
                  <CardTitle>Product {item.product_id}</CardTitle>
                  <CardDescription>Item #{item.id}</CardDescription>
                </CardHeader>
                <CardContent className="grid gap-6">
                  <div className="grid gap-2 text-sm">
                    <div className="flex items-center justify-between">
                      <span className="text-muted-foreground">Price</span>
                      <span className="font-medium">
                        {formatCurrency(item.price)}
                      </span>
                    </div>
                    <div className="flex items-center justify-between">
                      <span className="text-muted-foreground">Quantity</span>
                      <span className="font-medium">{item.quantity}</span>
                    </div>
                    <div className="flex items-center justify-between">
                      <span className="text-muted-foreground">Subtotal</span>
                      <span className="font-medium">
                        {formatCurrency(item.total)}
                      </span>
                    </div>
                  </div>

                  <Separator />

                  <div className="grid gap-4">
                    <div className="flex flex-wrap items-center justify-between gap-2">
                      <div>
                        <h2 className="text-sm font-semibold">
                          Add your review
                        </h2>
                        <p className="text-xs text-muted-foreground">
                          Share your experience with this product.
                        </p>
                      </div>
                      <Badge variant="outline">Purchased</Badge>
                    </div>

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

                      <div className="flex flex-wrap items-center gap-3">
                        <Button type="button">Submit review</Button>
                        <p className="text-xs text-muted-foreground">
                          Posting as {reviewerLabel}
                        </p>
                        <Badge variant={userId ? "secondary" : "outline"}>
                          {userId ? "Linked account" : "No account"}
                        </Badge>
                      </div>
                    </form>
                  </div>
                </CardContent>
              </Card>
            ))
          )}
        </div>

        <Card className="h-fit">
          <CardHeader>
            <CardTitle>Order summary</CardTitle>
            <CardDescription>Review details for this order.</CardDescription>
          </CardHeader>
          <CardContent className="grid gap-4 text-sm">
            <div className="flex items-center justify-between">
              <span className="text-muted-foreground">Order ID</span>
              <span className="font-medium">{order.id}</span>
            </div>
            <div className="flex items-center justify-between">
              <span className="text-muted-foreground">Customer</span>
              <span className="font-medium">{order.email}</span>
            </div>
            <div className="flex items-center justify-between">
              <span className="text-muted-foreground">Items total</span>
              <span className="font-medium">{formatCurrency(itemsTotal)}</span>
            </div>
            <div className="flex items-center justify-between">
              <span className="text-muted-foreground">Order total</span>
              <span className="font-semibold">
                {formatCurrency(order.total)}
              </span>
            </div>
            <Separator />
            <div className="grid gap-1 text-xs text-muted-foreground">
              <span>Placed on {formatDate(order.created_at)}</span>
              <span>Last updated {formatDate(order.updated_at)}</span>
            </div>
          </CardContent>
        </Card>
      </div>
    </div>
  );
};

export default OrderPage;
