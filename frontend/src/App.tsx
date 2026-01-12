import Home from "@/pages/Home";
import Products from "@/pages/Products";
import { Route, Routes } from "react-router";

export default function App() {
  return (
    <Routes>
      <Route index element={<Home />} />
      <Route path="products" element={<Products />} />
    </Routes>
  );
}
