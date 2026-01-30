import { AccountButton } from "@/components/account-button";
import { Button } from "@/components/ui/button";
import { useCart } from "@/hooks/useCart";
import { ShoppingBag } from "lucide-react";
import { Link, NavLink } from "react-router";

const Navbar = () => {
  const { cart } = useCart();

  const navItemClass =
    "relative px-1 py-1 font-medium after:absolute after:left-0 after:-bottom-1 after:h-[2px] after:w-full after:bg-black after:scale-x-0 after:origin-left after:transition-transform after:duration-300 hover:after:scale-x-100";

  return (
    <nav className="sticky top-0 z-50 bg-background outline">
      <div className="wrapper flex items-center justify-between h-16">
        <h1 className="font-serif font-medium text-2xl">MINT</h1>

        <div className="space-x-4 sm:space-x-6 lg:space-x-8">
          <NavLink
            to="/"
            className={({ isActive }) =>
              `${navItemClass} ${isActive ? "after:scale-x-100" : ""}`
            }
          >
            Home
          </NavLink>

          <NavLink
            to="/shop"
            className={({ isActive }) =>
              `${navItemClass} ${isActive ? "after:scale-x-100" : ""}`
            }
          >
            Shop
          </NavLink>

          <NavLink
            to="/about"
            className={({ isActive }) =>
              `${navItemClass} ${isActive ? "after:scale-x-100" : ""}`
            }
          >
            About
          </NavLink>
        </div>

        <div className="flex items-center gap-3">
          <AccountButton />

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
      </div>
    </nav>
  );
};

export default Navbar;
