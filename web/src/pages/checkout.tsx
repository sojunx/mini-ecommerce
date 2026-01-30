import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Separator } from "@/components/ui/separator";
import { useAuth } from "@/hooks/useAuth";
import { useCart } from "@/hooks/useCart";
import http from "@/lib/http";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";

const CheckoutPage = () => {
  const { cart, total, clearCart } = useCart();
  const { user } = useAuth();
  const navigate = useNavigate();
  const [isLoading, setIsLoading] = useState(false);
  const [email, setEmail] = useState("");
  const userId =
    typeof window !== "undefined" ? localStorage.getItem("user_id") : null;
  const isAnonymous = !userId;

  useEffect(() => {
    if (user?.email) {
      setEmail(user.email);
    }
  }, [user]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsLoading(true);

    try {
      const resolvedEmail = user?.email || email;
      const orderData = {
        email: resolvedEmail,
        items: cart.map((item) => ({
          product_id: item.id,
          quantity: item.quantity,
        })),
      };

      const response = await http.post("/api/orders", orderData);
      const orderId = response?.data?.order_id ?? response?.data?.id;
      const successOrderId = orderId ?? "unknown";
      const itemsSnapshot = cart;
      const totalSnapshot = total;
      clearCart();
      navigate(`/orders/${successOrderId}/success`, {
        state: {
          orderId,
          email: resolvedEmail,
          items: itemsSnapshot,
          total: totalSnapshot,
        },
      });
    } catch (error) {
      console.error("Order failed:", error);
      alert("Failed to place order. Please try again.");
    } finally {
      setIsLoading(false);
    }
  };

  if (cart.length === 0) {
    return (
      <div className="container mx-auto px-6 py-16">
        <div className="max-w-md mx-auto text-center bg-muted/40 border border-border rounded-2xl p-10">
          <h1 className="text-2xl font-semibold mb-3">No items to checkout</h1>
          <p className="text-muted-foreground mb-6">
            Your cart is empty. Add items before placing an order.
          </p>
          <Button onClick={() => navigate("/shop")}>Go to Shop</Button>
        </div>
      </div>
    );
  }

  return (
    <div className="container mx-auto px-6 py-12 max-w-6xl">
      <div className="flex items-center justify-between mb-8">
        <div>
          <h1 className="text-3xl font-semibold tracking-tight">Checkout</h1>
          <p className="text-sm text-muted-foreground mt-1">
            Complete your order and place it securely.
          </p>
        </div>
        <Button variant="outline" onClick={() => navigate("/cart")}>
          Back to Cart
        </Button>
      </div>

      <div className="grid lg:grid-cols-[1.1fr_0.9fr] gap-10">
        <form
          onSubmit={handleSubmit}
          className="bg-muted/40 border border-border rounded-2xl p-6 space-y-6"
        >
          <div>
            <h2 className="text-lg font-medium mb-4">Contact Information</h2>
            <div className="space-y-4">
              {isAnonymous ? (
                <div>
                  <Label htmlFor="email">Email</Label>
                  <Input
                    id="email"
                    name="email"
                    type="email"
                    required
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder="you@example.com"
                    className="bg-white"
                  />
                </div>
              ) : (
                <div className="rounded-xl border border-dashed border-border bg-background p-4 text-sm text-muted-foreground">
                  Using account email{" "}
                  <span className="font-medium">{email}</span>
                </div>
              )}
            </div>
          </div>

          <Button
            type="submit"
            className="w-full"
            size="lg"
            disabled={isLoading}
          >
            {isLoading ? "Processing..." : "Place Order"}
          </Button>
        </form>

        <div className="lg:sticky lg:top-24 h-fit">
          <div className="bg-muted/40 border border-border rounded-2xl p-6">
            <h2 className="text-lg font-medium mb-6">Order Summary</h2>

            <div className="space-y-4 mb-6">
              {cart.map((item) => (
                <div key={item.id} className="flex items-center gap-3">
                  <div className="w-14 h-14 bg-background rounded-md overflow-hidden shrink-0">
                    <img
                      src={item.image}
                      alt={item.name}
                      className="w-full h-full object-cover"
                      draggable={false}
                    />
                  </div>
                  <div className="flex-1">
                    <p className="text-sm font-medium truncate">{item.name}</p>
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

            <Separator className="my-6" />

            <div className="space-y-2">
              <div className="flex justify-between text-sm">
                <span className="text-muted-foreground">Subtotal</span>
                <span>${total.toFixed(2)}</span>
              </div>
              <div className="flex justify-between text-sm">
                <span className="text-muted-foreground">Shipping</span>
                <span>Free</span>
              </div>
              <Separator className="my-2" />
              <div className="flex justify-between text-lg font-semibold">
                <span>Total</span>
                <span>${total.toFixed(2)}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CheckoutPage;
