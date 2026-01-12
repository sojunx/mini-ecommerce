import Navbar from "@/components/navbar";
import { Button } from "@/components/ui/button";
import { ArrowRight } from "lucide-react";

const Home = () => {
  return (
    <main className="min-h-screen flex flex-col bg-white select-none">
      <Navbar />

      <section className="flex-1 flex items-center justify-center px-8 py-20">
        <div className="max-w-4xl text-center space-y-8">
          <div className="space-y-4">
            <h2 className="text-5xl font-bold tracking-tight text-gray-900">
              Discover Amazing Products
            </h2>
            <p className="text-xl text-gray-500 max-w-2xl mx-auto">
              Shop the latest trends and find everything you need in one place.
              Quality products, unbeatable prices.
            </p>
          </div>

          <div className="flex gap-4 justify-center">
            <Button size="lg" className="gap-2 bg-black hover:bg-gray-800">
              Shop Now
              <ArrowRight className="w-4 h-4" />
            </Button>
            <Button size="lg" variant="outline">
              Browse Collections
            </Button>
          </div>

          <div className="grid grid-cols-3 gap-8 pt-12 max-w-3xl mx-auto">
            <div className="space-y-2">
              <div className="text-3xl font-bold text-gray-900">500+</div>
              <div className="text-sm text-gray-500">Products</div>
            </div>
            <div className="space-y-2">
              <div className="text-3xl font-bold text-gray-900">50k+</div>
              <div className="text-sm text-gray-500">Happy Customers</div>
            </div>
            <div className="space-y-2">
              <div className="text-3xl font-bold text-gray-900">4.9★</div>
              <div className="text-sm text-gray-500">Average Rating</div>
            </div>
          </div>
        </div>
      </section>
    </main>
  );
};

export default Home;
