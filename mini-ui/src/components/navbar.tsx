import { Button } from "@/components/ui/button";
import UserButton from "@/components/user-button";
import { useAuth } from "@/hooks/useAuth";
import { ShoppingBag, User } from "lucide-react";
import { Link, NavLink } from "react-router";

const Navbar = () => {
  const { user } = useAuth();

  return (
    <nav className="sticky top-0 z-50 bg-background/30 backdrop-blur-md outline">
      <div className="wrapper flex items-center justify-between h-16">
        <h1 className="font-serif font-medium text-2xl">MINT</h1>

        <div className="space-x-4 sm:space-x-6 lg:space-x-8">
          <NavLink
            to="/"
            className={({ isActive }) =>
              `nav-link ${isActive ? "after:scale-x-100" : ""}`
            }
          >
            Home
          </NavLink>

          <NavLink
            to="/shop"
            className={({ isActive }) =>
              `nav-link ${isActive ? "after:scale-x-100" : ""}`
            }
          >
            Shop
          </NavLink>

          <NavLink
            to="/about"
            className={({ isActive }) =>
              `nav-link ${isActive ? "after:scale-x-100" : ""}`
            }
          >
            About
          </NavLink>
        </div>

        <div className="flex items-center gap-3">
          <Button asChild variant="ghost" className="rounded-full">
            <Link to="/cart" aria-label="Open shopping bag">
              <ShoppingBag />
            </Link>
          </Button>
          {!user && (
            <Button asChild size="sm">
              <Link to="/login">
                <User />
                Login
              </Link>
            </Button>
          )}

          <UserButton />
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
