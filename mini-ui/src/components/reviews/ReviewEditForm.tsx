import { Button } from "@/components/ui/button";
import { Textarea } from "@/components/ui/textarea";
import http from "@/lib/http";
import type { Review } from "@/types/review";
import { useEffect, useState, type FormEvent } from "react";
import { Link, useNavigate, useParams } from "react-router";
import { toast } from "sonner";

const ratingOptions = [1, 2, 3, 4, 5];

const ReviewEditForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);
  const [review, setReview] = useState<Review | null>(null);
  const [rating, setRating] = useState<number>(5);
  const [comment, setComment] = useState<string>("");

  useEffect(() => {
    if (!id) {
      setLoading(false);
      return;
    }

    const loadReview = async () => {
      try {
        const result = await http.get(`/api/reviews/${id}`);
        const data = (result?.data ?? result) as Review;

        setReview(data);
        setRating(data.rating ?? 5);
        setComment(data.comment ?? "");
      } catch (error: unknown) {
        toast.error((error as Error).message || "Failed to load review.");
      } finally {
        setLoading(false);
      }
    };

    loadReview();
  }, [id]);

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    if (!id) return;

    const trimmedComment = comment.trim();
    if (!trimmedComment) {
      toast.error("Comment cannot be empty.");
      return;
    }

    const formData = new FormData(event.currentTarget);
    formData.set("rating", String(rating));
    formData.set("comment", trimmedComment);

    const data = Object.fromEntries(formData.entries());

    setSubmitting(true);
    try {
      await http.put(`/api/reviews/${id}`, data);
      toast.success("Review updated successfully.");
      navigate(-1);
    } catch (error: unknown) {
      console.log(error)
      toast.error((error as Error).message || "Failed to update review.");
    } finally {
      setSubmitting(false);
    }
  };

  if (loading) {
    return <p className="text-sm text-muted-foreground">Loading...</p>;
  }

  if (!review) {
    return (
      <div className="space-y-3">
        <p className="text-sm text-muted-foreground">Review not found.</p>
        <Link to="/profile" className="text-sm text-primary hover:underline">
          Back to Profile
        </Link>
      </div>
    );
  }

  return (
    <form className="space-y-4" onSubmit={handleSubmit}>
      <div className="space-y-2">
        <p className="text-sm font-medium">Rating</p>
        <div className="flex flex-wrap items-center gap-2">
          {ratingOptions.map((value) => (
            <label
              key={value}
              className="flex cursor-pointer items-center gap-2 rounded-md border px-2 py-1 text-sm transition"
            >
              <input
                type="radio"
                name="rating"
                value={value}
                checked={rating === value}
                onChange={() => setRating(value)}
                className="h-4 w-4"
              />
              <span>{value}★</span>
            </label>
          ))}
        </div>
      </div>

      <div className="space-y-2">
        <label htmlFor="comment" className="text-sm font-medium">
          Comment
        </label>
        <Textarea
          id="comment"
          name="comment"
          value={comment}
          onChange={(event) => setComment(event.target.value)}
          placeholder="Update your comment..."
        />
      </div>

      <div className="flex items-center gap-2">
        <Button type="submit" disabled={submitting}>
          {submitting ? "Saving..." : "Save changes"}
        </Button>
        <Button
          type="button"
          variant="outline"
          onClick={() => navigate(-1)}
          disabled={submitting}
        >
          Cancel
        </Button>
      </div>
    </form>
  );
};

export default ReviewEditForm;
