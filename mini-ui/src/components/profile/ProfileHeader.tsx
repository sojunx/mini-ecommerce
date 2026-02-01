import ProfileActions from "@/components/profile/ProfileActions";
import ProfileInfo from "@/components/profile/ProfileInfo";
import ProfileStats from "@/components/profile/ProfileStats";
import { Separator } from "@/components/ui/separator";

const ProfileHeader = () => {
  return (
    <section className="rounded-3xl border bg-muted/40 p-6 md:p-10">
      <div className="flex flex-col gap-6 lg:flex-row lg:items-center lg:justify-between">
        <ProfileInfo />

        <ProfileActions />
      </div>

      <Separator className="my-6" />

      <ProfileStats />
    </section>
  );
};

export default ProfileHeader;
