import Navbar from "@/components/navbar";
import { Outlet } from "react-router";

const RootLayout = () => {
  return (
    <main className="w-full space-y-6 mb-16">
      <Navbar />

      <div className="wrapper">
        <Outlet />
      </div>
    </main>
  );
};

export default RootLayout;
