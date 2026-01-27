import { Button } from "@/components/ui/button";
import { useCart } from "@/hooks/useCart";
import { ShoppingBag } from "lucide-react";
import { Link } from "react-router";

const Navbar = () => {
  const { cart } = useCart();

  return (
    <nav className="outline">
      <div className="wrapper flex items-center justify-between h-16">
        <h1 className="font-serif font-medium text-2xl">MINT</h1>

        <div className="space-x-4 sm:space-x-6 lg:space-x-8">
          <Link to="/">Home</Link>
          <Link to="/shop">Shop</Link>
          <Link to="/about">About</Link>
        </div>

        <Button asChild variant="ghost" className="relative">
          <Link to="/cart" aria-label="Open shopping bag">
            <ShoppingBag />
            {cart.length > 0 && (
              <span className="absolute -top-1 -right-1 w-5 h-5 bg-primary text-primary-foreground text-xs rounded-full flex items-center justify-center">
                {cart.length}
              </span>
            )}
          </Link>
        </Button>
      </div>
    </nav>
  );
};

export default Navbar;
