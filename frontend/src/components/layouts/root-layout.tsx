import Navbar from "@/components/navbar";
import { Outlet } from "react-router";

const RootLayout = () => {
  return (
    <main className="h-screen gap-3 flex flex-col bg-white select-none overflow-hidden">
      <Navbar />

      <div className="flex-1 overflow-y-auto">
        <Outlet />
      </div>
    </main>
  );
};

export default RootLayout;
