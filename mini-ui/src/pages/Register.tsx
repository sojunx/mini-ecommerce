import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { useAuth } from "@/hooks/useAuth";
import { Label } from "@radix-ui/react-label";
import { useEffect, type FormEvent } from "react";

const RegisterPage = () => {
  const { user, register } = useAuth();

  useEffect(() => {
    if (user) window.location.href = "/";
  }, [user]);

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
    <div className="flex items-center justify-center">
      <form
        className="w-full max-w-md space-y-6 border bg-white p-10 rounded-xl shadow-sm"
        onSubmit={handleSubmit}
      >
        <div>
          <Label htmlFor="full_name">Full Name</Label>
          <Input
            id="full_name"
            name="full_name"
            type="text"
            required
            placeholder="Enter Your Full Name"
          />
        </div>

        <div>
          <Label htmlFor="email">Email</Label>
          <Input
            id="email"
            name="email"
            type="email"
            required
            placeholder="Enter Your Email"
          />
        </div>

        <div>
          <Label htmlFor="password">Password</Label>
          <Input
            id="password"
            name="password"
            type="password"
            required
            placeholder="Enter Your Password"
          />
        </div>

        <div>
          <Label htmlFor="confirm_password">Confirm Password</Label>
          <Input
            id="confirm_password"
            name="confirm_password"
            type="password"
            required
            placeholder="Enter Your Confirm Password"
          />
        </div>

        <Button className="w-full cursor-pointer" size="lg">
          Register
        </Button>
      </form>
    </div>
  );
};

export default RegisterPage;
