import axios from "axios";

export const http = axios.create({
  baseURL: "http://localhost:8080",
  withCredentials: true,
});

// let accessToken = "";

// http.interceptors.response.use(
//   (response) => response,
//   async (error) => {
//     if (error.response.status === 401) {
//       // 1. Try to refresh
//       const res = await axios.post("http://localhost:8080/api/auth/refresh");
//       accessToken = res.data.access_token;

//       // 2. Retry original request with new token
//       error.config.headers["Authorization"] = `Bearer ${accessToken}`;
//       return http(error.config);
//     }
//     return Promise.reject(error);
//   }
// );
