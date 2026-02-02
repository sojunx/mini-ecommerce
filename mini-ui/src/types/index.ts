export interface ApiResponse<T = unknown> {
  data?: T;
  error_msg?: string;
  message: string;
  success: boolean;
}
