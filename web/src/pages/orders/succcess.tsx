import { Button } from "@/components/ui/button";
import { Separator } from "@/components/ui/separator";
import { Check } from "lucide-react";
import { useLocation, useNavigate, useParams } from "react-router";

type OrderItem = {
  id: string;
  name: string;
  image: string;
  price: number;
  quantity: number;
};

type OrderSuccessState = {
  orderId?: string;
  email?: string;
  items?: OrderItem[];
  total?: number;
};

const OrderSuccessPage = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const params = useParams();
  const state = (location.state || {}) as OrderSuccessState;

  const items = state.items || [];
  const total = state.total ?? 0;
  const orderId = state.orderId || params.id || "—";

  if (items.length === 0) {
    return (
      <div className="container mx-auto px-6 py-16">
        <div className="max-w-md mx-auto text-center bg-muted/40 border border-border rounded-2xl p-10">
          <h1 className="text-2xl font-semibold mb-3">Order not found</h1>
          <p className="text-muted-foreground mb-6">
            We couldn’t find your order details. Please continue shopping.
          </p>
          <Button onClick={() => navigate("/shop")}>Go to Shop</Button>
        </div>
      </div>
    );
  }

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
          Your order number is <span className="font-medium">{orderId}</span>
        </p>

        <div className="mt-8 mx-auto max-w-md text-left bg-muted/30 border border-border rounded-2xl p-6">
          <p className="text-sm font-semibold">Order Summary</p>
          <div className="mt-4 space-y-5">
            {items.map((item) => (
              <div key={item.id} className="flex items-center gap-3">
                <div className="w-12 h-12 rounded-md overflow-hidden bg-muted">
                  <img
                    src={item.image}
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
                    Qty {item.quantity}
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
            <span>${total.toFixed(2)}</span>
          </div>
        </div>

        <div className="mt-8 flex flex-col sm:flex-row gap-4 justify-center">
          <Button onClick={() => navigate("/shop")}>Back to Shop</Button>
          <Button
            variant="secondary"
            onClick={() => navigate(`/orders/${orderId}`)}
            disabled={!orderId || orderId === "—"}
          >
            View Order
          </Button>
        </div>
      </div>
    </div>
  );
};

export default OrderSuccessPage;
