import ProductDetails from "@/components/product-details";
import ProductReviews from "@/components/product-reviews";
import type { Pageable } from "@/types";
import type { Product } from "@/types/product";
import type { Review, ReviewStats } from "@/types/review";
import { ChevronLeft } from "lucide-react";
import { Link, useLoaderData } from "react-router";

interface ProductPageData {
  product: Product;
  reviews: {
    content: Review[];
    page: Pageable;
  };
  stats: ReviewStats;
}

const ProductPage = () => {
  const { product, reviews, stats } = useLoaderData<ProductPageData>();

  if (!product) {
    return (
      <div className="text-sm text-muted-foreground">Product not found.</div>
    );
  }

  return (
    <div className="space-y-4">
      <Link
        to="/shop"
        className="group inline-flex items-center gap-1 text-muted-foreground transition-colors hover:text-foreground"
      >
        <ChevronLeft size={20} />
        <span className="relative">
          Back to Shop
          <span className="absolute left-0 -bottom-0.5 h-px w-0 bg-current transition-all duration-300 group-hover:w-full" />
        </span>
      </Link>

      <ProductDetails product={product} />

      <ProductReviews reviews={reviews} stats={stats} />
    </div>
  );
};

export default ProductPage;
