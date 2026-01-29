import { Button } from "@/components/ui/button";
import { Link } from "react-router";

const LandingPage = () => {
  return (
    <div className="space-y-20">
      <section className="min-h-[90vh] py-12 grid lg:grid-cols-[1.15fr_0.85fr] gap-14 items-center">
        <div className="space-y-8">
          <p className="text-sm uppercase tracking-widest text-muted-foreground">
            New Collection
          </p>

          <h1 className="text-4xl md:text-5xl font-semibold tracking-tight leading-tight">
            Everyday essentials,
            <br />
            designed to feel good.
          </h1>

          <p className="text-base text-muted-foreground leading-relaxed max-w-xl">
            Discover curated pieces built for comfort, style, and durability.
            Modern essentials tailored for your daily routine.
          </p>

          <div className="flex flex-col sm:flex-row gap-3">
            <Button asChild size="lg">
              <Link to="/shop">Shop Now</Link>
            </Button>

            <Button asChild size="lg" variant="outline">
              <Link to="/about">Learn More</Link>
            </Button>
          </div>

          <div className="flex flex-wrap gap-6 text-sm text-muted-foreground pt-2">
            <span>Free shipping</span>
            <span>30days returns</span>
            <span>Secure checkout</span>
          </div>
        </div>

        <div className="bg-muted/50 border border-border rounded-3xl p-4">
          <div className="aspect-4/3 rounded-2xl overflow-hidden relative">
            <img
              src="/slide.png"
              alt="Featured products"
              className="w-full h-full object-cover"
            />
            <div className="absolute inset-0 bg-linear-to-t from-black/30 to-transparent" />
          </div>

          <p className="mt-4 text-sm text-muted-foreground">
            Our best-selling essentials
          </p>
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
            desc: "Functional details and clean silhouettes for everyday wear.",
          },
          {
            title: "Fast Shipping",
            desc: "Free shipping on all orders with easy returns.",
          },
        ].map((item) => (
          <div
            key={item.title}
            className="rounded-2xl border bg-muted/40 p-6 space-y-2"
          >
            <h3 className="text-lg font-medium">{item.title}</h3>
            <p className="text-sm text-muted-foreground">{item.desc}</p>
          </div>
        ))}
      </section>

      <section className="text-center space-y-6">
        <p className="text-sm uppercase tracking-widest text-muted-foreground">
          Trusted by thousands
        </p>

        <h2 className="text-2xl font-semibold">Loved by customers worldwide</h2>

        <div className="flex justify-center flex-wrap gap-8 opacity-70">
          <span className="text-sm font-medium">10,000+ Customers</span>
          <span className="text-sm font-medium">4.8★ Average Rating</span>
          <span className="text-sm font-medium">Worldwide Shipping</span>
        </div>
      </section>

      <section className="rounded-3xl bg-primary text-primary-foreground p-10 md:p-14 text-center space-y-6">
        <h2 className="text-3xl font-semibold">
          Ready to upgrade your everyday essentials?
        </h2>

        <p className="text-sm opacity-90 max-w-xl mx-auto">
          Shop our most-loved products and experience the difference today.
        </p>

        <div className="flex justify-center">
          <Button asChild size="lg" variant="secondary">
            <Link to="/shop">Shop the Collection</Link>
          </Button>
        </div>
      </section>
    </div>
  );
};

export default LandingPage;
