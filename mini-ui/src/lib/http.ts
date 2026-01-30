import axios from "axios";

const baseURL = "http://localhost:8080";

const http = axios.create({ baseURL, withCredentials: true });

http.interceptors.response.use(
  (response) => response.data,
  (error) => Promise.reject(error.response?.data || error.message),
);

export default http;
