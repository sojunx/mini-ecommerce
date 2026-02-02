/* eslint-disable @typescript-eslint/no-unused-vars */
import http from "@/lib/http";
import type { ApiResponse } from "@/types";
import type { LoginRequest, RegisterRequest } from "@/types/auth";
import type { User } from "@/types/user";

const AuthService = {
  login: async (request: LoginRequest) => {
    try {
      const res = await http.post<ApiResponse>("/api/auth/login", request);

      return res.data;
    } catch (error: unknown) {
      throw new Error("Login failed. Please try again.");
    }
  },

  logout: async () => {
    try {
      const res = await http.post<ApiResponse>("/api/auth/logout");

      return res.data;
    } catch (error: unknown) {
      throw new Error("Logout failed. Please try again.");
    }
  },

  register: async (request: RegisterRequest) => {
    try {
      const res = await http.post<ApiResponse>("/api/auth/register", request);

      return res.data;
    } catch (error: unknown) {
      throw new Error("Registration failed. Please try again.");
    }
  },

  getCurrentUser: async () => {
    try {
      const res = await http.get<ApiResponse<User>>("/api/users/me");

      return res.data;
    } catch (error: unknown) {
      throw new Error("Failed to fetch current user. Please try again.");
    }
  },
};

export default AuthService;
