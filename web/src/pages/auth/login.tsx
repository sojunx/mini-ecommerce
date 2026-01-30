import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { useAuth } from "@/hooks/useAuth";
import { Label } from "@radix-ui/react-label";
import { Link } from "react-router";

const LoginPage = () => {
  const { login } = useAuth();

  return (
    <div className="flex items-center justify-center">
      <form
        className="w-full max-w-md space-y-6 border bg-white p-10 rounded-xl shadow-sm"
        onSubmit={login}
      >
        <div>
          <Label htmlFor="email">Email</Label>
          <Input
            id="email"
            name="email"
            type="email"
            required
            placeholder="you@example.com"
            className="bg-white"
          />
        </div>

        <Button className="w-full cursor-pointer" size="lg" type="submit">
          Login
        </Button>
        <span className="w-full flex justify-center text-sm text-gray-600">
          Don&apos;t have an account?
          <Link
            to="/register"
            className="ml-1 font-medium hover:opacity-85 duration-75 underline underline-offset-4"
          >
            Register here
          </Link>
        </span>
      </form>
    </div>
  );
};

export default LoginPage;
