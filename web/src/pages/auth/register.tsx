import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { useAuth } from "@/hooks/useAuth";
import { Label } from "@radix-ui/react-label";

const RegisterPage = () => {
  const { register } = useAuth();

  return (
    <div className="flex items-center justify-center">
      <form
        className="w-full max-w-md space-y-6 border bg-white p-10 rounded-xl shadow-sm"
        onSubmit={register}
      >
        <div>
          <Label htmlFor="email">Name</Label>
          <Input
            id="name"
            name="name"
            type="name"
            required
            placeholder="Your Name"
            className="bg-white"
          />
        </div>
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

        <Button className="w-full cursor-pointer" size="lg">
          Register
        </Button>
      </form>
    </div>
  );
};

export default RegisterPage;
