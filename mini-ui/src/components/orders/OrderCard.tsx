import { Badge } from "@/components/ui/badge";
import { formatDate, getOrderNum } from "@/lib/utils";
import type { Order } from "@/types/order";
import { Link } from "react-router";

const OrderCard = ({ order }: { order: Order }) => {
  return (
    <Link
      to={`/orders/${order.id}`}
      key={order.id}
      className="rounded-2xl border bg-background p-5 w-md max-md:w-full"
    >
      <div className="flex flex-col gap-4 md:flex-row md:items-center md:justify-between">
        <div className="space-y-2 w-full">
          <div className="flex w-full items-center justify-between">
            <h3 className="text-lg font-medium">
              ORDER - #{getOrderNum(order.id)}
            </h3>
            <Badge
              variant={order.status === "Delivered" ? "secondary" : "outline"}
            >
              {order.status}
            </Badge>
          </div>
          <p className="text-sm text-muted-foreground">
            {formatDate(order.created_at)}
          </p>
          <p className="text-lg font-semibold">Total: ${order.total}</p>
        </div>
      </div>
    </Link>
  );
};

export default OrderCard;
