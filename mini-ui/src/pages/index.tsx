import Navbar from "@/components/navbar";
import AuthProvider from "@/providers/auth-provider";
import CartProvider from "@/providers/cart-provider";
import { Outlet } from "react-router";

const RootLayout = () => {
  return (
    <AuthProvider>
      <CartProvider>
        <main className="space-y-8 pb-16">
          <Navbar />
          <div className="max-w-7xl mx-auto">
            <Outlet />
          </div>
        </main>
      </CartProvider>
    </AuthProvider>
  );
};

export default RootLayout;
