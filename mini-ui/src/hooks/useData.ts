import http from "@/lib/http";
import { useEffect, useState } from "react";

const useData = <T>(url: string) => {
  const [data, setData] = useState<T | null>(null);
  const [loading, setLoading] = useState<boolean>(false);

  useEffect(() => {
    const getData = async () => {
      setLoading(true);
      try {
        const res = await http.get<T>(url);
        setData(res.data);
      } catch (error: unknown) {
        console.error((error as Error).message);
      } finally {
        setLoading(false);
      }
    };

    getData();
  }, [url]);

  return { data, loading };
};

export default useData;
