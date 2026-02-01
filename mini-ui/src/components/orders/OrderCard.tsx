import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";

const OrderCard = ({ order }: { order: any }) => {
  return (
    <div key={order.id} className="rounded-2xl border bg-background p-5">
      <div className="flex flex-col gap-4 md:flex-row md:items-center md:justify-between">
        <div className="space-y-2">
          <div className="flex flex-wrap items-center gap-2">
            <h3 className="text-lg font-medium">{order.id}</h3>
            <Badge
              variant={order.status === "Delivered" ? "secondary" : "outline"}
            >
              {order.status}
            </Badge>
          </div>
          <p className="text-sm text-muted-foreground">
            {order.date} · {order.items} items
          </p>
        </div>

        <div className="flex flex-col sm:flex-row gap-3 sm:items-center">
          <p className="text-lg font-semibold">{order.total}</p>
          <Button size="sm" variant="outline">
            View details
          </Button>
          <Button size="sm">Track order</Button>
        </div>
      </div>
    </div>
  );
};

export default OrderCard;
