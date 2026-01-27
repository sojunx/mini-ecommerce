import { Button } from "@/components/ui/button";
import { Separator } from "@/components/ui/separator";
import { useCart } from "@/hooks/useCart";
import { useNavigate } from "react-router";

const CartPage = () => {
  const { cart, total, removeFromCart } = useCart();
  const navigate = useNavigate();

  if (cart.length === 0) {
    return (
      <div className="container mx-auto px-6 py-16">
        <div className="max-w-md mx-auto text-center bg-muted/40 border border-border rounded-2xl p-10">
          <h1 className="text-2xl font-semibold mb-3">Your cart is empty</h1>
          <p className="text-muted-foreground mb-6">
            Add some items to your cart to continue to checkout.
          </p>
          <Button onClick={() => navigate("/shop")}>Continue Shopping</Button>
        </div>
      </div>
    );
  }

  return (
    <div className="container mx-auto px-6 py-10 max-w-5xl">
      <div className="flex items-center justify-between mb-6">
        <div>
          <h1 className="text-3xl font-semibold tracking-tight">Your Cart</h1>
          <p className="text-sm text-muted-foreground mt-1">
            {cart.length} {cart.length === 1 ? "item" : "items"} in your bag
          </p>
        </div>
        <Button variant="outline" onClick={() => navigate("/shop")}>
          Continue Shopping
        </Button>
      </div>

      <div className="grid lg:grid-cols-[1.1fr_0.9fr] gap-8">
        <div className="bg-muted/40 border border-border rounded-2xl p-5">
          <h2 className="text-lg font-medium mb-6">Items</h2>
          <div className="space-y-6">
            {cart.map((item) => (
              <div key={item.id} className="flex gap-4">
                <div className="w-24 h-24 bg-background rounded-lg overflow-hidden shrink-0">
                  <img
                    src={item.image}
                    alt={item.name}
                    className="w-full h-full object-cover"
                    draggable={false}
                  />
                </div>
                <div className="flex-1">
                  <div className="flex items-start justify-between">
                    <div>
                      <h3 className="font-medium">{item.name}</h3>
                      <p className="text-sm text-muted-foreground mt-1">
                        Qty: {item.quantity}
                      </p>
                    </div>
                    <div className="text-right">
                      <p className="text-sm font-semibold">
                        ${(item.price * item.quantity).toFixed(2)}
                      </p>
                      <Button
                        type="button"
                        variant="ghost"
                        size="sm"
                        className="h-7 px-2 text-xs text-muted-foreground hover:text-foreground"
                        onClick={() => removeFromCart(item.id)}
                      >
                        Remove
                      </Button>
                    </div>
                  </div>
                  <p className="text-xs text-muted-foreground mt-2">
                    ${item.price.toFixed(2)} each
                  </p>
                </div>
              </div>
            ))}
          </div>
        </div>

        <div className="lg:sticky lg:top-24 h-fit">
          <div className="bg-muted/40 border border-border rounded-2xl p-5 space-y-5">
            <div>
              <h2 className="text-lg font-medium">Order Summary</h2>
              <p className="text-sm text-muted-foreground mt-1">
                Review your items before checkout.
              </p>
            </div>

            <div className="space-y-3">
              <div className="flex justify-between text-sm">
                <span className="text-muted-foreground">Subtotal</span>
                <span>${total.toFixed(2)}</span>
              </div>
              <div className="flex justify-between text-sm">
                <span className="text-muted-foreground">Shipping</span>
                <span>Free</span>
              </div>
              <Separator />
              <div className="flex justify-between text-lg font-semibold">
                <span>Total</span>
                <span>${total.toFixed(2)}</span>
              </div>
            </div>

            <Button
              className="w-full"
              size="lg"
              onClick={() => navigate("/checkout")}
            >
              Continue to Checkout
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CartPage;
