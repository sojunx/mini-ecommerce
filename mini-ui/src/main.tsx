import router from "@/router";
import { createRoot } from "react-dom/client";
import { RouterProvider } from "react-router";
import { Toaster } from "sonner";
import "./index.css";

const root = createRoot(document.getElementById("root")!);
root.render(
  <>
    <Toaster />
    <RouterProvider router={router} />
  </>,
);
