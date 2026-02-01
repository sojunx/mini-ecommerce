import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Badge } from "@/components/ui/badge";

const ProfileInfo = () => {
  return (
    <div className="flex flex-col sm:flex-row sm:items-center gap-4">
      <Avatar size="lg">
        <AvatarImage src="/avatar.png" alt="User avatar" />
        <AvatarFallback>JS</AvatarFallback>
      </Avatar>

      <div className="space-y-2">
        <div className="flex flex-wrap items-center gap-2">
          <h1 className="text-2xl md:text-3xl font-semibold tracking-tight">
            Welcome back, Jisoo
          </h1>
          <Badge variant="secondary">Premium Member</Badge>
        </div>
        <p className="text-sm text-muted-foreground">
          jisoo.smith@email.com · Joined Aug 2024
        </p>
        <div className="flex flex-wrap gap-2">
          <Badge variant="outline">Free shipping</Badge>
          <Badge variant="outline">Priority support</Badge>
        </div>
      </div>
    </div>
  );
};

export default ProfileInfo;
