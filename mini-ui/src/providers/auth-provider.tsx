import { AuthContext } from "@/hooks/useAuth";
import AuthService from "@/services/auth.service";
import type { LoginRequest, RegisterRequest } from "@/types/auth";
import type { User } from "@/types/user";
import { useEffect, useState, type ReactNode } from "react";
import { toast } from "sonner";

const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [user, setUser] = useState<User | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [initialized, setInitialized] = useState<boolean>(false);

  useEffect(() => {
    const getData = async () => {
      try {
        const data = await AuthService.getCurrentUser();

        setUser(data);
      } catch {
        setUser(null);
      } finally {
        setInitialized(true);
        setLoading(false);
      }
    };

    getData();
  }, []);

  const login = async (request: LoginRequest) => {
    setLoading(true);
    try {
      await AuthService.login(request);

      const user = await AuthService.getCurrentUser();
      setUser(user);
    } catch (error: unknown) {
      toast.error((error as Error).message);
    } finally {
      setLoading(false);
    }
  };

  const logout = async () =>
    await AuthService.logout().then(() => setUser(null));

  const register = async (request: RegisterRequest) =>
    await AuthService.register(request)
      .then(() => window.location.replace("/login"))
      .catch((error: unknown) => toast.error((error as Error).message));

  return (
    <AuthContext.Provider
      value={{ user, loading, initialized, login, logout, register }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
