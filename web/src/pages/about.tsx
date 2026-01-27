const AboutPage = () => {
  return (
    <div className="space-y-12">
      <section className="bg-muted/40 border border-border rounded-3xl p-10">
        <div className="max-w-2xl">
          <p className="text-sm uppercase tracking-widest text-muted-foreground">
            About Us
          </p>
          <h1 className="text-4xl font-semibold tracking-tight mt-2">
            A modern essentials brand built on comfort and simplicity.
          </h1>
          <p className="text-muted-foreground mt-4">
            We believe the best products are the ones that feel effortless to
            use, wear, and live with. Every piece we offer is designed to be
            timeless, functional, and responsibly made.
          </p>
        </div>
      </section>

      <section className="grid md:grid-cols-2 gap-8 items-center">
        <div className="space-y-4">
          <h2 className="text-2xl font-semibold">Our mission</h2>
          <p className="text-sm text-muted-foreground">
            Create products that make daily life feel better. We focus on clean
            design, quality materials, and fair pricing so you can shop with
            confidence.
          </p>
        </div>
        <div className="bg-muted/40 border border-border rounded-2xl p-6">
          <div className="grid grid-cols-2 gap-4">
            {[
              { label: "Happy customers", value: "12k+" },
              { label: "Products designed", value: "320" },
              { label: "Countries served", value: "28" },
              { label: "Years of craft", value: "8" },
            ].map((stat) => (
              <div
                key={stat.label}
                className="rounded-xl border border-border p-4"
              >
                <p className="text-xl font-semibold">{stat.value}</p>
                <p className="text-xs text-muted-foreground mt-1">
                  {stat.label}
                </p>
              </div>
            ))}
          </div>
        </div>
      </section>

      <section className="grid md:grid-cols-3 gap-6">
        {[
          {
            title: "Design-led",
            desc: "Every detail is intentional, from material to packaging.",
          },
          {
            title: "Responsibly made",
            desc: "We partner with suppliers who care about quality and people.",
          },
          {
            title: "Customer-first",
            desc: "We’re here to help at every step of your purchase.",
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
    </div>
  );
};

export default AboutPage;
