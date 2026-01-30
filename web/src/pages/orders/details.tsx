import { Badge } from "@/components/ui/badge";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { useAuth } from "@/hooks/useAuth";
import type { Order, OrderItem } from "@/lib/types";
import { formatCurrency, formatDate } from "@/lib/utils";
import RatingForm from "@/pages/orders/rating";
import { useLoaderData } from "react-router";

interface OrderPageLoaderData {
  order: Order;
  items: OrderItem[];
}

const OrderPage = () => {
  const { order, items } = useLoaderData<OrderPageLoaderData>();
  const { user } = useAuth();

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

          {!user && (
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
            items.map((item) => {
              console.log(item);
              return (
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
                      <div className="flex flex-col gap-1 items-end">
                        <Badge>
                          {item.reviewed ? "Reviewed" : "Not reviewed"}
                        </Badge>
                        <Badge variant="outline">
                          {formatCurrency(item.price * item.quantity)}
                        </Badge>
                      </div>
                    </div>
                  </CardHeader>

                  {!item.reviewed && (
                    <CardContent>
                      <RatingForm item={item} order={order} />
                    </CardContent>
                  )}
                </Card>
              );
            })
          )}
        </div>
      </div>
    </div>
  );
};

export default OrderPage;
