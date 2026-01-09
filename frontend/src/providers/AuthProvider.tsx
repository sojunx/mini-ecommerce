/* eslint-disable @typescript-eslint/no-unused-vars */
/* eslint-disable @typescript-eslint/no-explicit-any */

import { AuthContext } from "@/hooks/use-auth";
import type { User } from "@/lib/types";
import authService from "@/services/auth-service";
import { useState } from "react";

const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const [user, setUser] = useState<User | null>(null);
  const [loading, setLoading] = useState<boolean>(false);

  const signIn = async () => {
    setLoading(true);

    try {
      const data = await authService.signIn("user", "password");
      console.log(data);

      // setUser(data.user);
      return { success: true };
    } catch (error: any) {
      return { success: false };
    } finally {
      setLoading(false);
    }
  };

  return (
    <AuthContext.Provider value={{ user, loading, signIn }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
