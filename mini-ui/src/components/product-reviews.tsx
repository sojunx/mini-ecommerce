import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import {
  Pagination,
  PaginationContent,
  PaginationEllipsis,
  PaginationItem,
  PaginationLink,
  PaginationNext,
  PaginationPrevious,
} from "@/components/ui/pagination";
import { formatDate } from "@/lib/utils";
import type { Pageable } from "@/types";
import type { Review, ReviewStats } from "@/types/review";
import { Star } from "lucide-react";
import { useSearchParams } from "react-router";

interface ProductReviewsProps {
  reviews: {
    content: Review[];
    page: Pageable;
  };
  stats: ReviewStats;
}

const ProductReviews = ({ reviews, stats }: ProductReviewsProps) => {
  const [searchParams, setSearchParams] = useSearchParams();
  const currentPage = Number(searchParams.get("page") ?? 0);
  const ratingParam = searchParams.get("rating");
  const selectedRating = ratingParam ? Number(ratingParam) : null;
  const normalizedRating =
    selectedRating !== null && !Number.isNaN(selectedRating)
      ? selectedRating
      : null;

  function getVisiblePages(current: number, total: number) {
    if (total <= 3) {
      return Array.from({ length: total }, (_, i) => i);
    }

    if (current === 0) {
      return [0, 1, 2];
    }

    if (current === total - 1) {
      return [total - 3, total - 2, total - 1];
    }

    return [current - 1, current, current + 1];
  }

  const totalPages = reviews.page?.total_pages ?? 0;
  const pages = getVisiblePages(currentPage, totalPages);

  const buildPageHref = (page: number) => {
    const params = new URLSearchParams(searchParams);
    params.set("page", String(page));
    if (normalizedRating === null) {
      params.delete("rating");
    }
    return `?${params.toString()}`;
  };

  const handleSelectRating = (value: number | null) => {
    setSearchParams((prev) => {
      const params = new URLSearchParams(prev);
      params.set("page", "0");
      if (value === null) {
        params.delete("rating");
      } else {
        params.set("rating", String(value));
      }
      return params;
    });
  };

  return (
    <div className="space-y-4">
      <h1 className="text-2xl font-serif">Reviews</h1>

      {reviews.content?.length === 0 && (
        <p className="text-muted-foreground font-serif">
          There are no reviews for this product yet.
        </p>
      )}

      {reviews.content?.length > 0 && (
        <div>
          <div className="grid gap-6 md:grid-cols-[220px_1fr] items-center outline rounded-xl p-6">
            <div className="flex flex-col items-center text-center space-y-3">
              <h1 className="text-5xl font-semibold tracking-tight">
                {stats.average_rating.toFixed(1)}
              </h1>

              <div className="flex items-center gap-1">
                {[...Array(Math.floor(stats.average_rating))].map((_, i) => (
                  <Star key={i} className="star" />
                ))}
              </div>

              <p className="text-sm text-muted-foreground">
                {stats.total} reviews
              </p>
            </div>

            <div className="space-y-3">
              {[5, 4, 3, 2, 1].map((rating) => {
                const ratingData = stats.ratings_count.find(
                  (r) => r.rating === rating,
                );
                const count = ratingData?.count || 0;
                const percentage =
                  stats.total > 0 ? (count / stats.total) * 100 : 0;

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
            <div className="flex flex-wrap gap-2">
              {[1, 2, 3, 4, 5].map((rating) => (
                <label
                  key={rating}
                  className="group flex cursor-pointer items-center gap-2 rounded-md border border-border px-3 py-2 text-xs font-medium transition hover:border-primary"
                >
                  <input
                    type="radio"
                    value={rating}
                    name="filter-rating"
                    onChange={() => handleSelectRating(rating)}
                    checked={normalizedRating === rating}
                    className="peer sr-only"
                  />
                  <span className="text-amber-500">★</span>
                  <span className="text-foreground">{rating}</span>
                </label>
              ))}
              <button
                onClick={() => handleSelectRating(null)}
                className="border px-4 rounded-md flex justify-center items-center bg-red-400 text-white cursor-pointer"
              >
                <span>x</span>
              </button>
            </div>

            {reviews.content.map((review) => (
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

            <Pagination>
              <PaginationContent>
                <PaginationItem>
                  <PaginationPrevious
                    aria-disabled={currentPage === 0}
                    href={buildPageHref(Math.max(currentPage - 1, 0))}
                  />
                </PaginationItem>
                {pages.map((p) => (
                  <PaginationItem key={p}>
                    <PaginationLink
                      isActive={p === currentPage}
                      href={buildPageHref(p)}
                    >
                      {p + 1}
                    </PaginationLink>
                  </PaginationItem>
                ))}
                {totalPages > 3 && currentPage < totalPages - 1 && (
                  <PaginationItem>
                    <PaginationEllipsis />
                  </PaginationItem>
                )}
                <PaginationItem>
                  <PaginationNext
                    aria-disabled={currentPage === totalPages - 1}
                    href={buildPageHref(
                      Math.min(currentPage + 1, totalPages - 1),
                    )}
                  />
                </PaginationItem>
              </PaginationContent>
            </Pagination>
          </div>
        </div>
      )}
    </div>
  );
};

export default ProductReviews;
