import { AuthContext } from "@/hooks/useAuth";
import AuthService from "@/services/auth.service";
import type { LoginRequest, RegisterRequest } from "@/types/auth";
import type { User } from "@/types/user";
import { useEffect, useState, type ReactNode } from "react";
import { toast } from "sonner";

const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [user, setUser] = useState<User | null>(null);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    AuthService.getCurrentUser().then((res) => {
      setUser(res.data ?? null);
      setLoading(false);
    });
  }, []);

  const login = async (request: LoginRequest) =>
    await AuthService.login(request)
      .then(() => window.location.reload())
      .catch((error: unknown) => toast.error((error as Error).message));

  const logout = async () =>
    await AuthService.logout().then(() => setUser(null));

  const register = async (request: RegisterRequest) =>
    await AuthService.register(request)
      .then(() => window.location.replace("/login"))
      .catch((error: unknown) => toast.error((error as Error).message));

  return (
    <AuthContext.Provider value={{ user, loading, login, logout, register }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
