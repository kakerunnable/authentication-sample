import axios from "axios";
import AuthProcessor from "@/infrastructure/auth/authProcessor";

const isHandlerEnabled = (config: any) => {
  return !(config.hasOwnProperty("handlerEnabled") && !config.handlerEnabled);
};

// axiosInstance.get('/v2/api-endpoint', { handlerEnabled: false })

const instance = axios.create({
  baseURL: process.env.VUE_APP_RESOURCE_SERVER,
  withCredentials: true,
  timeout: 10000
});

const requestHandler = (request: any) => {
  if (isHandlerEnabled(request)) {
    console.log("Request Interceptor", request);
  }
  return request;
};

const errorHandler = (error: any) => {
  if (isHandlerEnabled(error.config)) {
    console.log("Error Interceptor", error);

    if (error.response) {
      if (error.response.status === 401) {
        AuthProcessor.performLogout();
      }
    }
  }
  return Promise.reject({ ...error });
};

const successHandler = (response: any) => {
  if (isHandlerEnabled(response.config)) {
    console.log("Response Interceptor", response);
  }
  return response;
};

instance.interceptors.request.use(request => requestHandler(request));

instance.interceptors.response.use(
  response => successHandler(response),
  error => errorHandler(error)
);

export default instance;
