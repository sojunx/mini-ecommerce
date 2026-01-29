import { Button } from "@/components/ui/button";
import { Link } from "react-router";

const HomePage = () => {
  return (
    <div className="space-y-16">
      <section className="grid lg:grid-cols-[1.1fr_0.9fr] gap-10 items-center">
        <div className="space-y-6">
          <p className="text-sm uppercase tracking-widest text-muted-foreground">
            New Collection
          </p>
          <h1 className="text-4xl md:text-5xl font-semibold tracking-tight">
            Everyday essentials, designed to feel good.
          </h1>
          <p className="text-muted-foreground text-base leading-relaxed">
            Discover curated pieces built for comfort, style, and durability.
            Shop modern essentials tailored for your daily routine.
          </p>
          <div className="flex flex-col sm:flex-row gap-3">
            <Button asChild size="lg">
              <Link to="/shop">Shop Now</Link>
            </Button>
            <Button asChild variant="outline" size="lg">
              <Link to="/about">Learn More</Link>
            </Button>
          </div>
        </div>

        <div className="bg-muted/60 border border-border rounded-3xl p-6">
          <div className="aspect-4/3 rounded-2xl overflow-hidden bg-muted">
            <img
              src="/hero.png"
              alt="Featured products"
              draggable={false}
              className="w-full h-full object-cover"
            />
          </div>
        </div>
      </section>

      <section className="grid md:grid-cols-3 gap-6">
        {[
          {
            title: "Premium Materials",
            desc: "Crafted with durable fabrics and finishes built to last.",
          },
          {
            title: "Thoughtful Design",
            desc: "Functional details and clean silhouettes for daily wear.",
          },
          {
            title: "Fast Shipping",
            desc: "Free shipping on all orders with easy returns.",
          },
        ].map((item) => (
          <div
            key={item.title}
            className="bg-muted/40 border border-border rounded-2xl p-6"
          >
            <h3 className="font-medium text-lg mb-2">{item.title}</h3>
            <p className="text-sm text-muted-foreground">{item.desc}</p>
          </div>
        ))}
      </section>

      <section className="bg-muted/40 border border-border rounded-3xl p-8 md:p-10">
        <div className="grid md:grid-cols-[1.1fr_0.9fr] gap-8 items-center">
          <div className="space-y-4">
            <h2 className="text-2xl font-semibold">Built for the long run</h2>
            <p className="text-sm text-muted-foreground">
              From design to delivery, we obsess over the details so you don’t
              have to. Shop our most-loved essentials today.
            </p>
          </div>
          <div className="flex flex-col sm:flex-row gap-3">
            <Button asChild size="lg">
              <Link to="/shop">Explore Products</Link>
            </Button>
            <Button asChild variant="outline" size="lg">
              <Link to="/cart">View Cart</Link>
            </Button>
          </div>
        </div>
      </section>
    </div>
  );
};

export default HomePage;
