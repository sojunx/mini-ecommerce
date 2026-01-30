import RootLayout from "@/layouts/root";
import http from "@/lib/http";
import AboutPage from "@/pages/about";
import LoginPage from "@/pages/auth/login";
import RegisterPage from "@/pages/auth/register";
import CartPage from "@/pages/cart";
import CheckoutPage from "@/pages/checkout";
import HomePage from "@/pages/home";
import OrderPage from "@/pages/orders/details";
import OrderSuccessPage from "@/pages/orders/succcess";
import ProductPage from "@/pages/products/details";
import ShopPage from "@/pages/products/shop";
import { createBrowserRouter } from "react-router";

const routes = createBrowserRouter([
  {
    path: "/",
    Component: RootLayout,
    children: [
      {
        index: true,
        Component: HomePage,
      },
      {
        path: "p/:id",
        Component: ProductPage,
        loader: async ({ params }) => {
          return {
            product: await http
              .get(`/api/products/${params.id}`)
              .then((res) => res.data),

            reviews: await http
              .get(`/api/reviews/${params.id}`)
              .then((res) => res.data),

            review_stats: await http
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
        path: "login",
        Component: LoginPage,
      },
      {
        path: "register",
        Component: RegisterPage,
      },
      {
        path: "checkout",
        Component: CheckoutPage,
      },
      {
        path: "orders/:id/success",
        Component: OrderSuccessPage,
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
        path: "cart",
        Component: CartPage,
      },
      {
        path: "shop",
        Component: ShopPage,
        loader: async () => {
          const res = await http.get("/api/products");

          return res.data;
        },
      },
      {
        path: "about",
        Component: AboutPage,
      },
    ],
  },
]);

export default routes;
