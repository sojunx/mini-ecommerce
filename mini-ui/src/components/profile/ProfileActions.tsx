import { Button } from "@/components/ui/button";

const ProfileActions = () => {
  return (
    <div className="flex flex-col sm:flex-row gap-3">
      <Button variant="outline">Edit profile</Button>
      <Button>Manage subscriptions</Button>
    </div>
  );
};

export default ProfileActions;
