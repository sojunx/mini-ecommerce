import LoginForm from "@/components/auth/LoginForm";
import { useAuth } from "@/hooks/useAuth";
import { useEffect } from "react";
import { Link } from "react-router";

const LoginPage = () => {
  const { user } = useAuth();

  useEffect(() => {
    if (user) window.location.href = "/";
  }, [user]);

  return (
    <div className="outline rounded-lg p-6 space-y-8 max-w-sm w-full">
      <h1 className="font-serif text-2xl text-center">LOGIN</h1>
      <LoginForm />

      <span className="w-full flex justify-center text-sm text-gray-600">
        Don&apos;t have an account?
        <Link
          to="/register"
          className="ml-1 font-medium hover:opacity-85 duration-75 underline underline-offset-4"
        >
          Register
        </Link>
      </span>
    </div>
  );
};

export default LoginPage;
