import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api",
});

api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && [401, 403].includes(error.response.status)) {
      window.location.href = "/login";
    } else if (error.message === "Network Error") {
      alert(
        "Network Error: Não foi possível conectar ao servidor. Verifique se o backend está rodando e se não há problemas de CORS."
      );
    }
    return Promise.reject(error);
  }
);

export default api;
