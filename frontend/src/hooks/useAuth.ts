import type { SignInCommand, User } from "@/lib/types";
import { createContext, useContext } from "react";

interface AuthContextProps {
  user: User | null;
  loading: boolean;
  signIn: (command: SignInCommand) => Promise<void>;
  signOut: () => Promise<void>;
}

export const AuthContext = createContext<AuthContextProps | null>(null);

export const useAuth = () => {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error("useAuth must be used within an AuthProvider");

  return ctx;
};
