import Navbar from "@/components/navbar";
import { http } from "@/lib/http";
import type { Product } from "@/lib/types";
import { useEffect, useState } from "react";

export default function Products() {
  const [products, setProducts] = useState<Product[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        setLoading(true);
        setError(null);
        const response = await http.get<{
          success: boolean;
          data: { products: Product[] };
          message: string;
        }>("/api/products");

        if (response.data.success) {
          setProducts(response.data.data.products);
        }
      } catch (err) {
        setError("Failed to load products");
        console.error("Error fetching products:", err);
      } finally {
        setLoading(false);
      }
    };

    fetchProducts();
  }, []);

  const getCategoryLabel = (category: string) => {
    return category.replace(/_/g, " ");
  };

  if (loading) {
    return (
      <main className="min-h-screen flex flex-col bg-white select-none">
        <Navbar />
        <div className="flex-1 flex items-center justify-center">
          <div className="text-gray-400">Loading products...</div>
        </div>
      </main>
    );
  }

  if (error) {
    return (
      <main className="min-h-screen flex flex-col bg-white select-none">
        <Navbar />
        <div className="flex-1 flex items-center justify-center">
          <div className="text-red-500">{error}</div>
        </div>
      </main>
    );
  }

  return (
    <main className="min-h-screen flex flex-col bg-white select-none">
      <Navbar />

      <section className="flex-1 px-8 py-12">
        <div className="max-w-7xl mx-auto space-y-8">
          <div className="space-y-2">
            <h1 className="text-4xl font-bold tracking-tight text-gray-900">
              Products
            </h1>
            <p className="text-gray-500">
              Browse our collection of {products.length} products
            </p>
          </div>

          <div className="grid gap-6">
            {products.map((product) => (
              <div
                key={product.id}
                className="group flex gap-6 p-6 border border-gray-200 rounded-lg hover:border-gray-300 transition-colors cursor-pointer"
              >
                <div className="shrink-0 w-32 h-32 bg-gray-100 rounded-md overflow-hidden">
                  <img
                    src={product.image_url}
                    alt={product.name}
                    className="w-full h-full object-cover group-hover:scale-105 transition-transform duration-300"
                  />
                </div>

                <div className="flex-1 min-w-0 space-y-2">
                  <div className="flex items-start justify-between gap-4">
                    <div className="space-y-1">
                      <h3 className="text-lg font-semibold text-gray-900 group-hover:text-gray-600 transition-colors">
                        {product.name}
                      </h3>
                      <p className="text-sm text-gray-500 uppercase tracking-wide">
                        {getCategoryLabel(product.category)}
                      </p>
                    </div>
                    <div className="text-lg font-bold text-gray-900 whitespace-nowrap">
                      ${product.base_price.toFixed(2)}
                    </div>
                  </div>

                  <p className="text-sm text-gray-600 line-clamp-2">
                    {product.description}
                  </p>

                  <div className="flex flex-wrap gap-2 pt-2">
                    {product.variants.slice(0, 4).map((variant) => (
                      <span
                        key={variant.sku}
                        className="inline-flex items-center gap-1 text-xs px-2 py-1 bg-gray-100 text-gray-700 rounded"
                      >
                        {variant.color}{" "}
                        {variant.size !== "One Size" && `· ${variant.size}`}
                      </span>
                    ))}
                    {product.variants.length > 4 && (
                      <span className="inline-flex items-center text-xs px-2 py-1 text-gray-500">
                        +{product.variants.length - 4} more
                      </span>
                    )}
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>
    </main>
  );
}
