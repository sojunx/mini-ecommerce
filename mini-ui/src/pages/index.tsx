import Loading from "@/components/loading";
import Navbar from "@/components/navbar";
import { useAuth } from "@/hooks/useAuth";
import { Outlet } from "react-router";

export const RootLayout = () => {
  const { initialized } = useAuth();

  if (!initialized) return <Loading />;

  return (
    <main className="space-y-4 pb-16">
      <Navbar />
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <Outlet />
      </div>
    </main>
  );
};

export const AuthLayout = () => {
  return (
    <div className="min-h-screen flex items-center justify-center">
      <Outlet />
    </div>
  );
};
