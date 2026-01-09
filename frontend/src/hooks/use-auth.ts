import type { User } from "@/lib/types";
import { createContext, useContext } from "react";

interface AuthContextProps {
  user: User | null;
  loading: boolean;
  signIn: () => Promise<{
    success: boolean;
  }>;
}

export const AuthContext = createContext<AuthContextProps | null>(null);

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) throw new Error("useAuth must be used within an AuthProvider");

  return context;
};
