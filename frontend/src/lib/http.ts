import axios from "axios";

export const http = axios.create({
  baseURL: "http://localhost:8080",
  withCredentials: true,
});

// Add a request interceptor to include the auth token in headers
http.interceptors.request.use(
  (config) => {
    const access_token = sessionStorage.getItem("access_token");
    if (access_token) config.headers.Authorization = `Bearer ${access_token}`;

    return config;
  },
  (error) => Promise.reject(error)
);
