import { Spinner } from "@/components/ui/spinner";

const Loading = () => {
  return (
    <div className="min-h-screen flex items-center justify-center gap-1">
      <Spinner className="size-6" />
      <h1 className="text-xl font-medium font-serif">Loading...</h1>
    </div>
  );
};

export default Loading;
