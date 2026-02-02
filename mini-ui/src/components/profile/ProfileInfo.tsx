import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { useAuth } from "@/hooks/useAuth";

const ProfileInfo = () => {
  const { user } = useAuth();

  return (
    <div className="flex flex-col sm:flex-row sm:items-center gap-4">
      <Avatar size="lg">
        <AvatarImage src="/avatar.png" alt="User avatar" />
        <AvatarFallback>JS</AvatarFallback>
      </Avatar>

      <div className="space-y-2">
        <h1 className="text-2xl md:text-3xl font-semibold tracking-tight">
          Welcome back, {user?.name}
        </h1>
        <p className="text-sm text-muted-foreground">
          {user?.email} · Joined Aug 2024
        </p>
      </div>
    </div>
  );
};

export default ProfileInfo;
