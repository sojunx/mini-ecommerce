import OrderProductRating from "@/components/order-product-rating";
import { Badge } from "@/components/ui/badge";
import { Separator } from "@/components/ui/separator";
import { getOrderNum } from "@/lib/utils";
import type { Order, OrderItem } from "@/types/order";
import { Package, ShoppingBasket } from "lucide-react";
import { useLoaderData } from "react-router";

interface OrderPageData {
  order: Order;
  items: OrderItem[];
}

const OrderPage = () => {
  const { order, items } = useLoaderData<OrderPageData>();

  return (
    <div className="mx-auto flex w-full max-w-3xl flex-col gap-6 p-4 sm:p-6 select-none">
      <header className="rounded-lg border bg-card p-4 shadow-xs">
        <div className="flex flex-wrap items-center justify-between gap-3">
          <div className="flex items-center gap-2 text-lg text-muted-foreground">
            <Package />
            <h1>ORDER - #{getOrderNum(order.id)}</h1>
          </div>

          <Badge variant="secondary">{order.status}</Badge>
        </div>
        <Separator className="my-4" />
        <div className="grid gap-3 text-sm sm:grid-cols-2">
          <div>
            <p className="text-muted-foreground">Email</p>
            <p className="font-medium">{order.email}</p>
          </div>
          <div>
            <p className="text-muted-foreground">Total</p>
            <p className="font-semibold">${order.total}</p>
          </div>
          <div>
            <p className="text-muted-foreground">Created</p>
            <p className="font-medium">
              {new Date(order.created_at).toLocaleString()}
            </p>
          </div>
          <div>
            <p className="text-muted-foreground">Updated</p>
            <p className="font-medium">
              {new Date(order.updated_at).toLocaleString()}
            </p>
          </div>
        </div>
      </header>

      <section className="rounded-lg border bg-card p-4 shadow-xs">
        <div className="text-muted-foreground text-lg flex items-center gap-2">
          <ShoppingBasket />
          <h2>PURCHASED ITEMS</h2>
        </div>

        <Separator className="my-4" />

        <div className="space-y-3">
          {items.map((item) => (
            <OrderProductRating key={item.id} order={order} item={item} />
          ))}
        </div>
      </section>
    </div>
  );
};

export default OrderPage;
