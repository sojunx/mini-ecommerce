import AuthProvider from "@/providers/auth-provider";
import CartProvider from "@/providers/cart-provider";
import routes from "@/routes";
import { createRoot } from "react-dom/client";
import { RouterProvider } from "react-router";
import "./index.css";

createRoot(document.getElementById("root")!).render(
  <AuthProvider>
    <CartProvider>
      <RouterProvider router={routes} />
    </CartProvider>
  </AuthProvider>,
);
