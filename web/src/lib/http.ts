import axios from "axios";

const http = axios.create({ baseURL: "http://localhost:8080" });

http.interceptors.response.use(
  (res) => res.data,
  (err) => Promise.reject(err.response?.data || err.message),
);

export default http;
