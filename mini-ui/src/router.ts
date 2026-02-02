import http from "@/lib/http";
import { AuthLayout, RootLayout } from "@/pages";
import AboutPage from "@/pages/About";
import CartPage from "@/pages/Cart";
import Home from "@/pages/Home";
import LoginPage from "@/pages/Login";
import OrderPage from "@/pages/Order";
import ProductPage from "@/pages/Product";
import ProfilePage from "@/pages/Profile";
import RegisterPage from "@/pages/Register";
import ShopPage from "@/pages/Shop";
import ProductService from "@/services/product.service";
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
        loader: async ({ request }) => {
          const url = new URL(request.url);
          const page = Number(url.searchParams.get("page") ?? 0);
          const name = url.searchParams.get("name")?.trim();

          if (name) {
            const result = await ProductService.searchProducts(name);

            if (result && "content" in result && "page" in result) {
              return result;
            }

            const list = Array.isArray(result)
              ? result
              : Array.isArray(result?.data)
                ? result.data
                : [];

            return {
              content: list,
              page: {
                size: list.length,
                number: 0,
                total_elements: list.length,
                total_pages: 1,
              },
            };
          }

          return await ProductService.getAllProducts(page);
        },
      },

      {
        path: "profile",
        Component: ProfilePage,
      },

      {
        path: "product/:id",
        Component: ProductPage,
        loader: async ({ params, request }) => {
          const url = new URL(request.url);
          const page = Number(url.searchParams.get("page") ?? 0);
          const rating = url.searchParams.get("rating");
          const ratingQuery = rating ? `&rating=${rating}` : "";

          return {
            product: await http
              .get(`/api/products/${params.id}`)
              .then((res) => res.data),
            reviews: await http
              .get(
                `/api/reviews/${params.id}?page=${page}&size=5${ratingQuery}`,
              )
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
    ],
  },

  {
    path: "/",
    Component: AuthLayout,
    children: [
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
