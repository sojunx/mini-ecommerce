import { Button } from "@/components/ui/button";
import { useAuth } from "@/hooks/use-auth";
import { ShoppingBag, User } from "lucide-react";

const Navbar = () => {
  const { user, signIn } = useAuth();

  return (
    <nav className="border-b w-full h-16 flex items-center justify-between px-8">
      <div className="flex items-center gap-2">
        <ShoppingBag className="w-5 h-5" />
        <h1 className="font-medium text-lg">Mini Ecommerce</h1>
      </div>

      <div className="flex gap-2 items-center">
        <Button variant="ghost">Home</Button>
        <Button variant="ghost">Products</Button>
        <Button variant="ghost">About</Button>

        {user && (
          <>
            <Button variant="outline" size="sm">
              <ShoppingBag className="w-4 h-4 mr-2" />
              Cart (0)
            </Button>
            <Button variant="outline" size="sm">
              <User className="w-4 h-4 mr-2" />
              Account
            </Button>
          </>
        )}

        {!user && (
          <Button variant="outline" size="sm" onClick={signIn}>
            Sign In
          </Button>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
