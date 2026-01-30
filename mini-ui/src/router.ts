import http from "@/lib/http";
import RootLayout from "@/pages";
import AboutPage from "@/pages/About";
import CartPage from "@/pages/Cart";
import Home from "@/pages/Home";
import LoginPage from "@/pages/Login";
import OrderPage from "@/pages/Order";
import ProductPage from "@/pages/Product";
import RegisterPage from "@/pages/Register";
import ShopPage from "@/pages/Shop";
import { createBrowserRouter } from "react-router";

const router = createBrowserRouter([
  {
    path: "/",
    Component: RootLayout,
    children: [
      { index: true, Component: Home },
      {
        path: "shop",
        Component: ShopPage,
        loader: async () => {
          return await http.get("/api/products").then((res) => res.data);
        },
      },

      {
        path: "product/:id",
        Component: ProductPage,
        loader: async ({ params }) => {
          return {
            product: await http
              .get(`/api/products/${params.id}`)
              .then((res) => res.data),
            reviews: await http
              .get(`/api/reviews/${params.id}`)
              .then((res) => res.data),
            stats: await http
              .get(`/api/reviews/${params.id}/stats`)
              .then((res) => res.data),
          };
        },
      },

      {
        path: "orders/:id",
        Component: OrderPage,
        loader: async ({ params }) => {
          return {
            order: await http
              .get(`/api/orders/${params.id}`)
              .then((res) => res.data),

            items: await http
              .get(`/api/orders/${params.id}/items`)
              .then((res) => res.data),
          };
        },
      },

      {
        path: "about",
        Component: AboutPage,
      },

      {
        path: "cart",
        Component: CartPage,
      },

      {
        path: "login",
        Component: LoginPage,
      },
      {
        path: "register",
        Component: RegisterPage,
      },
    ],
  },
]);

export default router;
