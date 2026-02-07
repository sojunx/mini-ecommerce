import RegisterForm from "@/components/auth/RegisterForm";
import { useAuth } from "@/hooks/useAuth";
import { useEffect } from "react";
import { Link } from "react-router";

const RegisterPage = () => {
  const { user } = useAuth();

  useEffect(() => {
    if (user) window.location.href = "/";
  }, [user]);

  return (
    <div className="outline rounded-lg p-6 space-y-8 max-w-sm w-full">
      <h1 className="font-serif text-2xl text-center">REGISTER</h1>
      <RegisterForm />

      <span className="w-full flex justify-center text-sm text-gray-600">
        Already have an account?
        <Link
          to="/login"
          className="ml-1 font-medium hover:opacity-85 duration-75 underline underline-offset-4"
        >
          Login
        </Link>
      </span>
    </div>
  );
};

export default RegisterPage;
