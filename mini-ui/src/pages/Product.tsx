import ProductDetails from "@/components/product-details";
import ProductReviews from "@/components/product-reviews";
import type { Product } from "@/types/product";
import type { Review, ReviewStats } from "@/types/review";
import { useLoaderData } from "react-router";

interface ProductPageData {
  product: Product;
  reviews: Review[];
  stats: ReviewStats;
}

const ProductPage = () => {
  const { product, reviews, stats } = useLoaderData<ProductPageData>();

  return (
    <div className="space-y-8">
      <ProductDetails product={product} />

      <ProductReviews reviews={reviews} stats={stats} />
    </div>
  );
};

export default ProductPage;
