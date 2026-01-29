import { AuthContext } from "@/hooks/useAuth";
import http from "@/lib/http";
import type { User } from "@/lib/types";
import { useEffect, useState } from "react";

const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const [user, setUser] = useState<User | null>(null);

  useEffect(() => {
    const getData = async () => {
      const id = localStorage.getItem("user_id");
      if (!id) return;

      const res = await http.get(`/api/users/${id}`);
      setUser(res.data);
    };

    getData();
  }, []);

  const login = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const formData = new FormData(e.currentTarget);
    const email = formData.get("email") as string;

    try {
      const res = await http.post("/api/users/login", { email });

      localStorage.setItem("user_id", res.data.id);
      window.location.href = "/";
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <AuthContext.Provider value={{ user, login }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
