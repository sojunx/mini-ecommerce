import OrderCard from "@/components/orders/OrderCard";
import ProfileHeader from "@/components/profile/ProfileHeader";
import ReviewCard from "@/components/reviews/ReviewCard";
import { Button } from "@/components/ui/button";

const orders = [
  {
    id: "ORD-1024",
    date: "Jan 18, 2026",
    status: "Delivered",
    total: "$124.00",
    items: 3,
  },
  {
    id: "ORD-1017",
    date: "Jan 03, 2026",
    status: "Processing",
    total: "$86.50",
    items: 2,
  },
  {
    id: "ORD-1002",
    date: "Dec 22, 2025",
    status: "Delivered",
    total: "$214.00",
    items: 4,
  },
];

const reviews = [
  {
    product: "Everyday Canvas Tote",
    rating: 5,
    date: "Jan 14, 2026",
    comment: "Sturdy and stylish. Perfect size for daily essentials.",
  },
  {
    product: "Soft Knit Hoodie",
    rating: 4,
    date: "Jan 02, 2026",
    comment: "Very cozy. The fit is relaxed and the fabric feels premium.",
  },
];

const ProfilePage = () => {
  return (
    <div className="space-y-12">
      <ProfileHeader />

      <section className="space-y-6">
        <div className="flex flex-col gap-2 md:flex-row md:items-end md:justify-between">
          <div>
            <h2 className="text-2xl font-semibold">My Orders</h2>
            <p className="text-sm text-muted-foreground">
              Track recent purchases and manage returns.
            </p>
          </div>
          <Button variant="outline">View all orders</Button>
        </div>

        <div className="grid gap-4">
          {orders.map((order) => (
            <OrderCard key={order.id} order={order} />
          ))}
        </div>
      </section>

      <section className="space-y-6">
        <div className="flex flex-col gap-2 md:flex-row md:items-end md:justify-between">
          <div>
            <h2 className="text-2xl font-semibold">My Reviews</h2>
            <p className="text-sm text-muted-foreground">
              Share feedback to help shoppers choose with confidence.
            </p>
          </div>
          <Button variant="outline">Write a review</Button>
        </div>

        <div className="grid gap-4 md:grid-cols-2">
          {reviews.map((review) => (
            <ReviewCard key={review.product} review={review} />
          ))}
        </div>
      </section>
    </div>
  );
};

export default ProfilePage;
