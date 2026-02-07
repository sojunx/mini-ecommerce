const ProfileStats = () => {
  return (
    <div className="grid gap-4 md:grid-cols-3">
      {[
        { label: "Orders", value: "24", detail: "2 in progress" },
        { label: "Reviews", value: "12", detail: "Avg 4.8★" },
        { label: "Rewards", value: "1,420", detail: "points balance" },
      ].map((stat) => (
        <div key={stat.label} className="rounded-2xl border bg-background p-4">
          <p className="text-sm text-muted-foreground">{stat.label}</p>
          <p className="text-2xl font-semibold">{stat.value}</p>
          <p className="text-xs text-muted-foreground">{stat.detail}</p>
        </div>
      ))}
    </div>
  );
};

export default ProfileStats;
