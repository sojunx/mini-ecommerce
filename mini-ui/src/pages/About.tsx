const AboutPage = () => {
  return (
    <div className="space-y-16">
      <section className="bg-muted/40 border border-border rounded-3xl p-12">
        <div className="max-w-3xl space-y-4">
          <p className="text-sm uppercase tracking-widest text-muted-foreground">
            Welcome
          </p>
          <h1 className="text-4xl md:text-5xl font-semibold tracking-tight">
            Modern products designed for everyday comfort
          </h1>
          <p className="text-muted-foreground max-w-2xl">
            Discover thoughtfully designed essentials made to simplify your
            daily life — clean, functional, and built to last.
          </p>

          <div className="flex gap-4 pt-4">
            <button className="px-6 py-3 rounded-xl bg-primary text-primary-foreground">
              Shop now
            </button>
            <button className="px-6 py-3 rounded-xl border border-border">
              Learn more
            </button>
          </div>
        </div>
      </section>

      <section className="grid md:grid-cols-3 gap-6">
        {[
          {
            title: "Minimal design",
            desc: "Clean aesthetics that never go out of style.",
          },
          {
            title: "Premium quality",
            desc: "Carefully selected materials for lasting comfort.",
          },
          {
            title: "Fair pricing",
            desc: "No middlemen. Quality you can trust.",
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

      <section className="bg-muted/40 border border-border rounded-3xl p-10">
        <div className="grid grid-cols-2 md:grid-cols-4 gap-6">
          {[
            { label: "Customers", value: "12k+" },
            { label: "Products", value: "320+" },
            { label: "Reviews", value: "4.9/5" },
            { label: "Years", value: "8+" },
          ].map((stat) => (
            <div
              key={stat.label}
              className="rounded-xl border border-border p-6 text-center"
            >
              <p className="text-2xl font-semibold">{stat.value}</p>
              <p className="text-xs text-muted-foreground mt-1">{stat.label}</p>
            </div>
          ))}
        </div>
      </section>

      {/* CTA */}
      <section className="text-center space-y-4">
        <h2 className="text-3xl font-semibold tracking-tight">
          Ready to elevate your everyday essentials?
        </h2>
        <p className="text-muted-foreground max-w-xl mx-auto">
          Join thousands of customers who trust our products for comfort,
          simplicity, and quality.
        </p>
        <button className="px-8 py-3 rounded-xl bg-primary text-primary-foreground">
          Get started
        </button>
      </section>
    </div>
  );
};

export default AboutPage;
