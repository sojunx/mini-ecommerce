import RootLayout from "@/layouts/root";
import http from "@/lib/http";
import AboutPage from "@/pages/about";
import CartPage from "@/pages/cart";
import CheckoutPage from "@/pages/checkout";
import HomePage from "@/pages/home";
import OrderSuccessPage from "@/pages/order-success";
import ProductPage from "@/pages/product";
import ShopPage from "@/pages/shop";
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
        path: "checkout",
        Component: CheckoutPage,
      },
      {
        path: "order-success",
        Component: OrderSuccessPage,
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
