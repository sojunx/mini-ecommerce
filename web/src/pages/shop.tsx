import type { Product } from "@/lib/types";
import { Link, useLoaderData } from "react-router";

const ShopPage = () => {
  const data = useLoaderData<Product[]>();

  return (
    <div className="space-y-10">
      <div className="flex flex-col gap-4 sm:flex-row sm:items-end sm:justify-between">
        <div>
          <h1 className="text-4xl font-semibold tracking-tight">Shop</h1>
          <p className="text-sm text-muted-foreground mt-2">
            Discover essentials crafted for everyday comfort.
          </p>
        </div>
      </div>

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
        {data.map((p) => (
          <Link
            to={`/p/${p.id}`}
            key={p.id}
            className="group bg-muted/40 border border-border rounded-2xl overflow-hidden transition hover:shadow-md"
          >
            <div className="overflow-hidden">
              <img
                src={p.image}
                alt={p.name}
                draggable={false}
                className="w-full aspect-square object-cover"
              />
            </div>

            <div className="p-4 space-y-2">
              <h3 className="font-medium leading-tight line-clamp-2">
                {p.name}
              </h3>
              <p className="text-xs text-muted-foreground line-clamp-2">
                {p.description}
              </p>
              <div className="flex items-center justify-between pt-2">
                <span className="text-lg font-semibold">
                  ${p.price.toFixed(2)}
                </span>
                <span className="text-xs text-muted-foreground">View</span>
              </div>
            </div>
          </Link>
        ))}
      </div>
    </div>
  );
};

export default ShopPage;
