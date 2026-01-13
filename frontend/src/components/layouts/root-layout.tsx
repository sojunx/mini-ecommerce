import Navbar from "@/components/navbar";
import { Outlet } from "react-router";

const RootLayout = () => {
  return (
    <main className="min-h-screen gap-3 flex flex-col bg-white select-none">
      <Navbar />
      <Outlet />
    </main>
  );
};

export default RootLayout;
