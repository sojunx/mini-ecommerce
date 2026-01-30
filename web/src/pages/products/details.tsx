import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { useCart } from "@/hooks/useCart";
import type { ProductDetails } from "@/lib/types";
import { formatDate } from "@/lib/utils";
import { ChevronLeft, Star } from "lucide-react";
import { Link, useLoaderData, useNavigate } from "react-router";

const ProductPage = () => {
  const { product, reviews, review_stats } = useLoaderData<ProductDetails>();
  const { addToCart } = useCart();

  const navigate = useNavigate();

  return (
    <div className="space-y-8">
      <Link
        to="/shop"
        className="gap-1 inline-flex items-center text-sm text-muted-foreground hover:text-foreground"
      >
        <ChevronLeft size={16} />
        Back to Shop
      </Link>

      <div className="grid lg:grid-cols-[0.95fr_1.05fr] gap-10 items-start">
        {/* Image Section */}
        <div className="bg-muted/50 border border-border rounded-2xl p-4 max-w-md mx-auto w-full">
          <div className="aspect-square w-full relative overflow-hidden rounded-xl bg-muted">
            <img
              draggable={false}
              src={product.image}
              alt={product.name}
              className="w-full h-full object-cover"
            />
          </div>
        </div>

        {/* Product Info Section */}
        <div className="flex flex-col h-full justify-between gap-8">
          {/* Product info */}
          <div className="space-y-5">
            <Badge variant="secondary" className="w-fit px-3 py-1 text-xs">
              ✨ New Arrival
            </Badge>

            <h1 className="text-4xl font-bold leading-tight tracking-tight">
              {product.name}
            </h1>

            <div className="flex items-center gap-4">
              <p className="text-3xl font-semibold text-primary">
                ${product.price.toFixed(2)}
              </p>
              <span className="text-sm text-muted-foreground">
                FREE SHIPPING
              </span>
            </div>
          </div>

          <div className="rounded-2xl border bg-muted/30 p-6">
            <h2 className="mb-3 text-sm font-semibold text-muted-foreground">
              Description
            </h2>
            <p className="text-base leading-relaxed text-foreground/90">
              {product.description}
            </p>
          </div>

          <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
            <Button
              size="lg"
              className="w-full text-base font-semibold"
              onClick={() => addToCart(product)}
            >
              Add to Cart
            </Button>

            <Button
              size="lg"
              variant="outline"
              className="w-full text-base font-semibold"
              onClick={() => {
                addToCart(product);
                navigate("/cart");
              }}
            >
              Buy Now
            </Button>
          </div>
        </div>
      </div>

      {/* Review section */}
      <div className="space-y-3">
        <h1 className="text-2xl font-serif">Reviews</h1>

        <div>
          <div className="grid gap-6 md:grid-cols-[220px_1fr] items-center outline rounded-xl p-6">
            <div className="flex flex-col items-center text-center space-y-3">
              <h1 className="text-5xl font-semibold tracking-tight">
                {review_stats.average_rating.toFixed(1)}
              </h1>

              <div className="flex items-center gap-1">
                {[...Array(Math.floor(review_stats.average_rating))].map(
                  (_, i) => (
                    <Star key={i} className="star" />
                  ),
                )}
              </div>

              <p className="text-sm text-muted-foreground">
                {review_stats.total} reviews
              </p>
            </div>

            <div className="space-y-3">
              {[5, 4, 3, 2, 1].map((rating) => {
                const ratingData = review_stats.ratings_count.find(
                  (r) => r.rating === rating,
                );
                const count = ratingData?.count || 0;
                const percentage =
                  review_stats.total > 0
                    ? (count / review_stats.total) * 100
                    : 0;

                return (
                  <div key={rating} className="flex items-center gap-3">
                    <div className="flex items-center gap-1 w-12 text-sm font-medium">
                      <span>{rating}</span>
                      <Star size={16} className="star" />
                    </div>

                    <div className="flex-1 h-2.5 outline bg-background rounded-full overflow-hidden">
                      <div
                        className="h-full bg-muted-foreground transition-all"
                        style={{ width: `${percentage}%` }}
                      />
                    </div>

                    <span className="text-sm text-muted-foreground w-16 text-right">
                      {count}
                    </span>
                  </div>
                );
              })}
            </div>
          </div>

          <div className="mt-6 space-y-4">
            {reviews.map((review) => (
              <div
                key={review.id}
                className="rounded-xl border bg-background p-5 space-y-3"
              >
                {/* Rating */}
                <div className="flex items-center gap-1">
                  {[1, 2, 3, 4, 5].map((star) => (
                    <Star
                      key={star}
                      className={`h-4 w-4 ${
                        star <= review.rating
                          ? "fill-yellow-500 text-yellow-500"
                          : "text-gray-300"
                      }`}
                    />
                  ))}
                </div>

                {/* Comment */}
                <p className="text-sm leading-relaxed text-foreground/90">
                  {review.comment}
                </p>

                {/* User info */}
                <div className="flex items-center gap-3 pt-2">
                  <Avatar className="h-9 w-9 border">
                    <AvatarImage src="/avatar2.png" />
                    <AvatarFallback>
                      {review.email.charAt(0).toUpperCase()}
                    </AvatarFallback>
                  </Avatar>

                  <div>
                    <p className="text-sm font-medium">{review.email}</p>
                    <p className="text-xs text-muted-foreground">
                      {formatDate(review.created_at)}
                    </p>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProductPage;
