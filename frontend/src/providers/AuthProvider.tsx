/* eslint-disable @typescript-eslint/no-explicit-any */

import Loading from "@/components/loading";
import { AuthContext } from "@/hooks/useAuth";
import { http } from "@/lib/http";
import type { User } from "@/lib/types";
import { useEffect, useState } from "react";
import { toast } from "sonner";

const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const [user, setUser] = useState<User | null>(null);
  const [loading, setLoading] = useState<boolean>(false);
  const [initLoading, setInitLoading] = useState<boolean>(true);

  useEffect(() => {
    const init = async () => {
      const token = sessionStorage.getItem("token");

      try {
        if (!token) await refresh();

        await getCurrentUser();
      } catch (error: any) {
        console.log(error.response);
      } finally {
        setInitLoading(false);
      }
    };

    init();
  }, []);

  const refresh = async () => {
    try {
      setLoading(true);
      const res = await http.post("/api/auth/refresh");

      const data = res.data?.data;
      if (data?.token) sessionStorage.setItem("token", data.token);
    } catch (error: any) {
      console.log(error.response);
    } finally {
      setLoading(false);
    }
  };

  const getCurrentUser = async () => {
    try {
      setLoading(true);
      const res = await http.get("/api/users/me");

      const data = res.data?.data;
      if (data) setUser(data);
    } catch (error: any) {
      console.log(error.response);
    } finally {
      setLoading(false);
    }
  };

  const signIn = async (email: string, password: string) => {
    try {
      setLoading(true);
      const res = await http.post("/api/auth/sign-in", { email, password });

      const { data, message } = res.data;
      if (data?.token) sessionStorage.setItem("token", data.token);

      window.location.reload();

      toast.success(message || "Signed in successfully");
    } catch (error: any) {
      console.log(error.response.data);
    } finally {
      setLoading(false);
    }
  };

  const signOut = async () => {
    try {
      const res = await http.post("/api/auth/sign-out");

      const { message, success } = res.data;

      if (!success) return;

      sessionStorage.removeItem("token");
      window.location.reload();

      toast.success(message || "Signed out successfully");
    } catch (error: any) {
      console.log(error.response);
    }
  };

  return (
    <AuthContext.Provider value={{ user, loading, signIn, signOut }}>
      {initLoading ? <Loading /> : children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
