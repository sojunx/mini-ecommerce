import { Button } from "@/components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { useAuth } from "@/hooks/useAuth";
import { LogOut, Settings, ShoppingBag, User, UserCircle } from "lucide-react";
import { Link } from "react-router";

const Navbar = () => {
  const { user, signIn, signOut } = useAuth();

  return (
    <nav className="border-b w-full h-16 flex items-center justify-between px-8 select-none">
      <div className="flex items-center gap-2">
        <ShoppingBag className="w-5 h-5" />
        <h1 className="font-medium text-lg">Mini Ecommerce</h1>
      </div>

      <div className="flex gap-2 items-center">
        <Button variant="ghost">Home</Button>
        <Button variant="ghost" asChild>
          <Link to="/products">Products</Link>
        </Button>
        <Button
          variant="ghost"
          onClick={() => console.log(sessionStorage.getItem("token"))}
        >
          About
        </Button>

        {user && (
          <>
            <Button variant="outline" size="sm">
              <ShoppingBag className="w-4 h-4 mr-2" />
              Cart (0)
            </Button>

            <DropdownMenu>
              <DropdownMenuTrigger asChild>
                <Button variant="outline" size="sm">
                  <User className="w-4 h-4 mr-2" />
                  Account
                </Button>
              </DropdownMenuTrigger>
              <DropdownMenuContent align="end" className="w-56 select-none">
                <DropdownMenuLabel>
                  <div className="flex flex-col space-y-1">
                    <p className="text-sm font-medium">
                      {user.first_name || "Van Anh"}{" "}
                      {user.last_name || "Nguyen"}
                    </p>
                    <p className="text-xs text-gray-500">{user.email}</p>
                    <p className="text-xs text-gray-400 capitalize">
                      {user.role}
                    </p>
                  </div>
                </DropdownMenuLabel>
                <DropdownMenuSeparator />
                <DropdownMenuItem asChild>
                  <Link to="/profile">
                    <UserCircle className="w-4 h-4 mr-2" />
                    <span>Profile</span>
                  </Link>
                </DropdownMenuItem>
                <DropdownMenuItem asChild>
                  <Link to="/settings">
                    <Settings className="w-4 h-4 mr-2" />
                    <span>Settings</span>
                  </Link>
                </DropdownMenuItem>
                <DropdownMenuSeparator />
                <DropdownMenuItem onClick={signOut}>
                  <LogOut className="w-4 h-4 mr-2" />
                  Sign Out
                </DropdownMenuItem>
              </DropdownMenuContent>
            </DropdownMenu>
          </>
        )}

        {!user && (
          <Button
            variant="outline"
            size="sm"
            onClick={() =>
              signIn({ email: "test.user@mail.com", password: "password" })
            }
          >
            Sign In
          </Button>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
