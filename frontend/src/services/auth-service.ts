/* eslint-disable @typescript-eslint/no-unused-vars, @typescript-eslint/no-explicit-any */

import { http } from "@/lib/http";

const authService = {
  async signIn(email: string, password: string) {
    try {
      const data = { email, password };
      const response = await http.post("/api/auth/sign-in", data);

      return response.data;
    } catch (error: any) {
      throw new Error("Sign in failed");
    }
  },

  async signOut() {
    try {
      const response = await http.post("/api/auth/sign-out");

      return response.data;
    } catch (error: any) {
      throw new Error("Sign out failed");
    }
  },

  async signUp(email: string, password: string) {
    try {
      const data = { email, password };
      const response = await http.post("/api/auth/sign-up", data);

      return response.data;
    } catch (error: any) {
      throw new Error("Sign up failed");
    }
  },

  async getCurrentUser() {},
};

export default authService;
