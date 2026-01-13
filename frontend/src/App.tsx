import Home from "@/pages/Home";
import ProductDetails from "@/pages/ProductDetails";
import Products from "@/pages/Products";
import { Route, Routes } from "react-router";

export default function App() {
  return (
    <Routes>
      <Route index element={<Home />} />

      <Route path="products">
        <Route index element={<Products />} />
        <Route path=":id" element={<ProductDetails />} />
      </Route>
    </Routes>
  );
}
