export interface ApiResponse<T = unknown> {
  data?: T;
  error_msg?: string;
  message: string;
  success: boolean;
}

export interface Pageable {
  size: number;
  number: number;
  total_elements: number;
  total_pages: number;
}
