import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { useAuth } from "@/hooks/useAuth";
import { Label } from "@radix-ui/react-label";
import { type FormEvent } from "react";

const RegisterForm = () => {
  const { register, loading } = useAuth();

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const formData = new FormData(e.currentTarget);
    const email = formData.get("email") as string;
    const full_name = formData.get("full_name") as string;
    const password = formData.get("password") as string;
    const confirm_password = formData.get("confirm_password") as string;

    await register({ email, full_name, password, confirm_password });
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-4">
      <div className="space-y-1">
        <Label htmlFor="full_name" className="text-sm font-medium">
          Full Name
        </Label>
        <Input
          id="full_name"
          name="full_name"
          type="text"
          required
          placeholder="Enter Your Full Name"
        />
      </div>

      <div className="space-y-1">
        <Label htmlFor="email" className="text-sm font-medium">
          Email
        </Label>
        <Input
          id="email"
          name="email"
          type="email"
          required
          placeholder="Enter your email"
        />
      </div>

      <div className="space-y-1">
        <Label htmlFor="password" className="text-sm font-medium">
          Password
        </Label>
        <Input
          id="password"
          name="password"
          type="password"
          required
          placeholder="Enter Your Password"
        />
      </div>

      <div className="space-y-1">
        <Label htmlFor="confirm_password" className="text-sm font-medium">
          Confirm Password
        </Label>
        <Input
          id="confirm_password"
          name="confirm_password"
          type="password"
          required
          placeholder="Enter Your Confirm Password"
        />
      </div>

      <Button
        className="w-full cursor-pointer"
        type="submit"
        disabled={loading}
      >
        {loading ? "Registering..." : "Register"}
      </Button>
    </form>
  );
};

export default RegisterForm;
