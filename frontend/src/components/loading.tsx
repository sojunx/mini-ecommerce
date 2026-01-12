import { Spinner } from "@/components/ui/spinner";

const Loading = () => {
  return (
    <div className="min-h-screen flex items-center justify-center gap-1">
      <Spinner className="size-6!" />
      <span className="font-mono font-medium text-lg">Loading...</span>
    </div>
  );
};

export default Loading;
