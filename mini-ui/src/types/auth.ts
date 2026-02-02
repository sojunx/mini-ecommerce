import type { User } from "@/types/user";

export interface AuthContextType {
  user: User | null;
  loading: boolean;
  login: (request: LoginRequest) => Promise<string | number | void>;
  logout: () => Promise<void>;
  register: (request: RegisterRequest) => Promise<string | number | void>;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  full_name: string;
  email: string;
  password: string;
  confirm_password: string;
}
