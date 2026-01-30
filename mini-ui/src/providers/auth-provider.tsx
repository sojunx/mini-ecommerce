import { AuthContext } from "@/hooks/useAuth";
import http from "@/lib/http";
import type { LoginRequest, RegisterRequest } from "@/types/auth";
import type { User } from "@/types/user";
import { useEffect, useState, type ReactNode } from "react";
import { useNavigate } from "react-router";

const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [user, setUser] = useState<User | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const navigate = useNavigate();

  useEffect(() => {
    const getData = async () => {
      try {
        const res = await http.get<User>("/api/users/me");

        setUser(res.data);
      } catch (error) {
        console.log(error);
      } finally {
        setLoading(false);
      }
    };

    getData();
  }, []);

  const login = async (request: LoginRequest) => {
    try {
      const res = await http.post<User>("/api/users/login", request);

      setUser(res.data);
      navigate("/");
    } catch (error) {
      console.log(error);
      alert("Login failed. Please try again.");
    }
  };

  const logout = async () => {
    try {
      await http.post("/api/users/logout");
      setUser(null);
      navigate("/");
    } catch (error) {
      console.log(error);
      alert("Logout failed. Please try again.");
    }
  };

  const register = async (request: RegisterRequest) => {
    try {
      await http.post<User>("/api/users/register", request);

      navigate("/login");
    } catch (error) {
      console.log(error);
      alert("Registration failed. Please try again.");
    }
  };

  return (
    <AuthContext.Provider value={{ user, loading, login, logout, register }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
