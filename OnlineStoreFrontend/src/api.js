import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080",
});

api.interceptors.request.use((config) => {
  const email = sessionStorage.getItem("email");
  const password = sessionStorage.getItem("password");

  if (email && password) {
    config.auth = {
      username: email,
      password: password,
    };
  }

  return config;
});

export default api;