import OrderCard from "@/components/orders/OrderCard";
import ProfileHeader from "@/components/profile/ProfileHeader";
import ReviewCard from "@/components/reviews/ReviewCard";
import { Button } from "@/components/ui/button";
import { useAuth } from "@/hooks/useAuth";
import type { Order } from "@/types/order";
import type { Review } from "@/types/review";
import { useEffect } from "react";
import { useLoaderData, useNavigate } from "react-router";

const ProfilePage = () => {
  const { user } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!user) navigate("/login");
  }, [navigate, user]);

  const { orders, reviews } = useLoaderData<{
    orders: Order[];
    reviews: Review[];
  }>();

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
            <ReviewCard key={review.id} review={review} />
          ))}
        </div>
      </section>
    </div>
  );
};

export default ProfilePage;
