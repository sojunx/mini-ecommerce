import ReviewEditForm from "@/components/reviews/ReviewEditForm";

const ReviewEditPage = () => {
  return (
    <div className="space-y-4">
      <div>
        <h1 className="text-2xl font-serif">Edit Review</h1>
        <p className="text-sm text-muted-foreground">
          Update your rating and comment.
        </p>
      </div>
      <ReviewEditForm />
    </div>
  );
};

export default ReviewEditPage;
