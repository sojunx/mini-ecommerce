import axios from "axios";

export const http = axios.create({
  baseURL: "http://localhost:8080",
  withCredentials: true,
});

// Add a request interceptor to include the auth token in headers
http.interceptors.request.use(
  (config) => {
    const token = sessionStorage.getItem("token");
    if (token) config.headers.Authorization = `Bearer ${token}`;

    return config;
  },
  (error) => Promise.reject(error)
);
