import RootLayout from "@/components/layouts/root-layout";
import AuthProvider from "@/providers/AuthProvider";
import { createRoot } from "react-dom/client";
import { BrowserRouter, Route, Routes } from "react-router";
import { Toaster } from "sonner";
import App from "./App.tsx";
import "./index.css";

createRoot(document.getElementById("root")!).render(
  // <StrictMode>
  <BrowserRouter>
    <Toaster />
    <AuthProvider>
      <Routes>
        <Route element={<RootLayout />}>
          <Route path="/*" element={<App />} />
        </Route>
      </Routes>
    </AuthProvider>
  </BrowserRouter>
  // </StrictMode>
);
