import { Button } from "@/components/ui/button";
import { Separator } from "@/components/ui/separator";
import type { Order, OrderItem } from "@/lib/types";
import { Check } from "lucide-react";
import { Link, useLoaderData } from "react-router";

const OrderSuccessPage = () => {
  const { order, items } = useLoaderData<{
    order: Order;
    items: OrderItem[];
  }>();

  const order_num = order.id.slice(0, 8).toUpperCase();

  return (
    <div className="container mx-auto px-6 py-16">
      <div className="mx-auto max-w-3xl bg-background border border-border rounded-3xl shadow-sm p-12 text-center">
        <div className="mx-auto w-16 h-16 rounded-full bg-primary/10 flex items-center justify-center">
          <Check className="text-primary" size={28} />
        </div>
        <h1 className="text-2xl md:text-3xl font-semibold mt-6">
          Thank you for your purchase
        </h1>
        <p className="text-sm text-muted-foreground mt-3">
          We’ve received your order will ship in 5–7 business days.
        </p>
        <p className="text-sm text-muted-foreground mt-2">
          Your order number is <span className="font-medium">#{order_num}</span>
        </p>

        <div className="mt-8 mx-auto max-w-md text-left bg-muted/30 border border-border rounded-2xl p-6">
          <p className="text-sm font-semibold">Order Summary</p>
          <div className="mt-4 space-y-5">
            {items.map((item) => (
              <div key={item.id} className="flex items-center gap-3">
                <div className="w-12 h-12 rounded-md overflow-hidden bg-muted">
                  <img
                    src={"/product.jpg"}
                    alt={item.name}
                    className="w-full h-full object-cover"
                    draggable={false}
                  />
                </div>
                <div className="flex-1">
                  <p className="text-sm font-medium line-clamp-1">
                    {item.name}
                  </p>
                  <p className="text-xs text-muted-foreground">
                    Quantity: {item.quantity}
                  </p>
                </div>
                <p className="text-sm font-medium">
                  ${(item.price * item.quantity).toFixed(2)}
                </p>
              </div>
            ))}
          </div>

          <Separator className="my-5" />
          <div className="flex items-center justify-between text-sm font-semibold">
            <span>Total</span>
            <span>${order.total.toFixed(2)}</span>
          </div>
        </div>

        <div className="mt-8 flex flex-col sm:flex-row gap-4 justify-center">
          <Button asChild>
            <Link to="/shop">Back to Shop</Link>
          </Button>

          <Button variant="secondary" asChild>
            <Link to={`/orders/${order.id}`}>View Order</Link>
          </Button>
        </div>
      </div>
    </div>
  );
};

export default OrderSuccessPage;
