import { Button } from "@/components/ui/button";
import { Separator } from "@/components/ui/separator";
import { Truck } from "lucide-react";
import { useState } from "react";
import { useLocation, useNavigate } from "react-router";

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
  const state = (location.state || {}) as OrderSuccessState;

  const items = state.items || [];
  const total = state.total ?? 0;
  const email = state.email || "";
  const orderId = state.orderId || "—";
  const [copied, setCopied] = useState(false);

  const handleCopyOrderId = async () => {
    if (!orderId || orderId === "—") return;
    try {
      await navigator.clipboard.writeText(orderId);
      setCopied(true);
      setTimeout(() => setCopied(false), 1500);
    } catch {
      setCopied(false);
    }
  };

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

  const primaryItem = items[0];

  return (
    <div className="container mx-auto px-6 py-12 max-w-6xl">
      <div className="relative overflow-hidden rounded-3xl bg-muted/60 border border-border p-8 md:p-12">
        <div className="absolute -top-12 -left-12 w-32 h-32 rounded-full bg-primary/10" />
        <div className="absolute -bottom-16 -right-12 w-40 h-40 rounded-full bg-primary/10" />

        <div className="relative z-10 grid gap-8 lg:grid-cols-[1.4fr_0.6fr] items-center">
          <div className="bg-background border border-border rounded-2xl shadow-sm p-8 text-center">
            <div className="mx-auto w-24 h-24 rounded-full bg-primary/10 flex items-center justify-center">
              <Truck className="text-primary" size={36} />
            </div>
            <h1 className="text-2xl md:text-3xl font-semibold mt-5">
              Your order has been placed
            </h1>
            <p className="text-sm text-muted-foreground mt-2">
              Thank you for your purchase. We’ll send a tracking number to
              {email ? ` ${email}` : " your email"} once your order ships.
            </p>
            <p className="text-xs text-muted-foreground mt-3">
              Estimated delivery: 2–4 business days. Need help? Contact our
              support team with your order ID.
            </p>

            <div className="flex items-center justify-center gap-6 mt-6 text-sm">
              <div className="text-center">
                <p className="text-muted-foreground">Order ID</p>
                <div className="flex items-center gap-2 justify-center">
                  <p className="font-medium">{orderId}</p>
                  <Button
                    type="button"
                    variant="ghost"
                    size="sm"
                    className="h-7 px-2 text-xs"
                    onClick={handleCopyOrderId}
                  >
                    {copied ? "Copied" : "Copy"}
                  </Button>
                </div>
              </div>
              <Separator orientation="vertical" className="h-8" />
              <div>
                <p className="text-muted-foreground">Total</p>
                <p className="font-medium">${total.toFixed(2)}</p>
              </div>
            </div>

            <div className="mt-8 flex flex-col sm:flex-row gap-3 justify-center">
              <Button onClick={() => navigate("/shop")}>
                Continue Shopping
              </Button>
              <Button variant="outline" onClick={() => navigate("/cart")}>
                View Order
              </Button>
            </div>
          </div>

          <div className="relative">
            <div className="bg-background border border-border rounded-2xl shadow-sm p-5">
              <p className="text-xs font-semibold text-muted-foreground">
                PRODUCT
              </p>
              <div className="flex items-center gap-3 mt-3">
                <div className="w-14 h-14 rounded-lg overflow-hidden bg-muted">
                  <img
                    src={primaryItem.image}
                    alt={primaryItem.name}
                    className="w-full h-full object-cover"
                    draggable={false}
                  />
                </div>
                <div className="flex-1">
                  <p className="text-sm font-medium line-clamp-1">
                    {primaryItem.name}
                  </p>
                  <p className="text-xs text-muted-foreground mt-1">
                    Qty {primaryItem.quantity}
                  </p>
                </div>
                <p className="text-sm font-medium">
                  ${(primaryItem.price * primaryItem.quantity).toFixed(2)}
                </p>
              </div>
            </div>

            <div className="hidden lg:block absolute -bottom-10 -left-10 bg-background border border-border rounded-2xl shadow-sm p-4 w-52">
              <p className="text-xs font-semibold text-muted-foreground">
                PAYMENT
              </p>
              <div className="text-xs text-muted-foreground mt-3 space-y-2">
                <div className="flex items-center justify-between">
                  <span>Method</span>
                  <span>Card</span>
                </div>
                <div className="flex items-center justify-between">
                  <span>Card</span>
                  <span>**** 9843</span>
                </div>
                <div className="flex items-center justify-between">
                  <span>Status</span>
                  <span>Paid</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default OrderSuccessPage;
