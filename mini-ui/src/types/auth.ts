import type { User } from "@/types/user";

export interface AuthContextType {
  user: User | null;
  loading: boolean;
  login: (request: LoginRequest) => Promise<void>;
  register: (request: RegisterRequest) => Promise<void>;
  logout: () => void;
}

export interface LoginRequest {
  email: string;
}

export interface RegisterRequest {
  name: string;
  email: string;
}
