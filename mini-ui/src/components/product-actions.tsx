import { Button } from "@/components/ui/button";
import { useAuth } from "@/hooks/useAuth";
import { useCart } from "@/hooks/useCart";
import type { Product } from "@/types/product";
import { useNavigate } from "react-router";

const ProductActions = ({ product }: { product: Product }) => {
  const { addToCart } = useCart();
  const { user } = useAuth();
  const navigate = useNavigate();

  return (
    <div className="space-y-8">
      <p className="text-4xl font-medium text-center">${product.price}</p>

      {user && (
        <div className="space-y-3">
          <Button className="w-full" onClick={() => addToCart(product)}>
            Add to Cart
          </Button>
          <Button
            variant={"outline"}
            className="w-full"
            onClick={() => {
              addToCart(product);
              navigate("/cart");
            }}
          >
            Buy Now
          </Button>
        </div>
      )}

      {!user && (
        <Button className="w-full" onClick={() => navigate("/login")}>
          Log in to purchase
        </Button>
      )}
    </div>
  );
};

export default ProductActions;
