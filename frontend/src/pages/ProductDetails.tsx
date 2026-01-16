import ProductReview from "@/components/ProductReview";
import { Button } from "@/components/ui/button";
import { mockProducts } from "@/lib/data";
import { mockReviews } from "@/lib/reviews";
import type { Product } from "@/lib/types";
import { Star } from "lucide-react";
import { useEffect, useState } from "react";
import { useParams } from "react-router";

const ProductDetails = () => {
  const { id } = useParams<{ id: string }>();
  const [product, setProduct] = useState<Product | null>(null);

  const totalReviews = mockReviews.length;

  const averageRating =
    mockReviews.length > 0
      ? mockReviews.reduce((sum, r) => sum + r.rating, 0) / mockReviews.length
      : 0;

  useEffect(() => {
    const foundProduct = mockProducts.data.products.find((p) => p.id === id);

    if (!foundProduct) return;

    setProduct(foundProduct);
  }, [id]);

  if (!product) return <div>loading...</div>;

  return (
    <>
      <div className="max-w-7xl mx-auto mt-10">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-12 items-start">
          <div className="flex justify-center">
            <div className="w-full max-w-md bg-white rounded-xl shadow-md overflow-hidden">
              <img
                src="https://binhminhdigital.com/StoreData/PageData/3429/Tim-hieu-ve-ban-quyen-hinh-anh%20(3).jpg"
                alt={product.name}
                className="w-full h-120 object-cover"
              />
            </div>
          </div>

          <div className="space-y-6">
            <h1 className="text-3xl font-semibold text-gray-800">
              {product.name}
            </h1>

            <div className="space-y-2 text-gray-600">
              <p>
                <span className="font-medium">Category:</span>{" "}
                {product.category}
              </p>
              <p>
                <span className="font-medium">SKU:</span> {product.sku}
              </p>
            </div>

            <div className="text-3xl text-[#ee4d2d] font-bold">
              ${product.price.toLocaleString("vi-VN")}
            </div>

            <div className="flex items-center gap-3">
              <div className="flex items-center gap-1 text-yellow-500">
                {[1, 2, 3, 4, 5].map((star) => (
                  <Star
                    key={star}
                    size={18}
                    className={
                      star <= Math.floor(averageRating)
                        ? "fill-yellow-500 text-yellow-500"
                        : "text-gray-300"
                    }
                  />
                ))}
              </div>

              <span className="text-sm font-medium text-gray-700">
                {averageRating.toFixed(1)}/5
              </span>

              <span className="text-sm text-gray-500">
                ({totalReviews} reviews)
              </span>
            </div>

            <p className="text-gray-700 leading-relaxed">
              <span className="font-medium">Description:</span>{" "}
              {product.description}
            </p>

            <div className="flex gap-4 pt-4">
              <Button size="xl" variant="outline" className="cursor-pointer">
                Add to Cart
              </Button>
              <Button
                size="xl"
                className="gap-2 bg-black hover:bg-gray-800 cursor-pointer"
              >
                Buy Now
              </Button>
            </div>
          </div>
        </div>
      </div>

      <div>
        <ProductReview />
      </div>
    </>
  );
};

export default ProductDetails;
