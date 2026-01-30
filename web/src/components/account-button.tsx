import { Button } from "@/components/ui/button";
import { useAuth } from "@/hooks/useAuth";
import { User } from "lucide-react";
import { Link } from "react-router";

export const AccountButton = () => {
  const { user, logout } = useAuth();

  if (!user)
    return (
      <Button asChild size="sm">
        <Link to="/login">
          <User />
          Login
        </Link>
      </Button>
    );

  return (
    <div className="space-x-1">
      <span className="text-sm font-medium">Hello, {user.name}</span>
      <Button variant={"outline"} onClick={logout}>
        Logout
      </Button>
    </div>
  );
};
