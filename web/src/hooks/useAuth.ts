import type { User } from "@/lib/types";
import { createContext, useContext, type FormEvent } from "react";

interface AuthContextType {
  user: User | null;
  login: (e: FormEvent<HTMLFormElement>) => Promise<void>;
}

export const AuthContext = createContext<AuthContextType | null>(null);

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) throw new Error("useAuth must be used within an AuthProvider");

  return context;
};
