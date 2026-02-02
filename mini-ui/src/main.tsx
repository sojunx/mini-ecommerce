import AuthProvider from "@/providers/auth-provider";
import CartProvider from "@/providers/cart-provider";
import router from "@/router";
import { createRoot } from "react-dom/client";
import { RouterProvider } from "react-router";
import { Toaster } from "sonner";
import "./index.css";

const root = createRoot(document.getElementById("root")!);
root.render(
  <AuthProvider>
    <CartProvider>
      <Toaster />
      <RouterProvider router={router} />
    </CartProvider>
  </AuthProvider>,
);
