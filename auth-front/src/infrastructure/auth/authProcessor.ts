import store from "../../store";
import httpResource from "../http/httpResource";

class AuthProcessor {

  private static INSTANCE: AuthProcessor;

  private constructor() {}

  public static get instance(): AuthProcessor {
    if (!this.INSTANCE) {
      this.INSTANCE = new AuthProcessor();
    }
    return this.INSTANCE;
  }

  private readonly intervalMilliSeconds = 1800000; // 30 minutes

  public parseApierror(error: any) {
    console.log("parseapierror", error);
    try {
      if (
          error &&
          error.hasOwnProperty("response") &&
          error.response.hasOwnProperty("data")
      ) {
        const apierror = error.response.data;
        return {
          status: apierror.status,
          statusCode: error.status,
          timestamp: apierror.timestamp,
          message: apierror.message
        };
      }
    } catch (parseError) {
      return {
        status: "INTERNAL_SERVER_ERROR",
        statusCode: 500,
        timestamp: new Date(),
        message: "Server is not responding.."
      };
    }
  }

  public performLogout() {
    const intervalName = store.getters.getIntervalName;
    if (intervalName) {
      clearInterval(intervalName);
    }
    store.commit("setIsAuthenticated", false);
    store.commit("clearCurrentUser");
  }

  public async refreshTokenInternal() {
    try {
      const response = await httpResource.post("/auth/refresh");
      if (response.status !== 200) this.performLogout();
    } catch (error) {
      this.performLogout();
    }
  }

  public async refreshToken() {
    const response = await httpResource.post("/auth/refresh");
    return response.status;
  }

  public async getAuthenticatedUser() {
    try {
      const response = await httpResource.get("/profile/me");
      if (response.status === 200) {
        const currentUser = response.data;
        store.commit("setCurrentUser", currentUser);
        store.commit("setIsAuthenticated", true);
        await this.refreshTokenInternal();
        const intervalName = setInterval(async () => {
          await this.refreshTokenInternal();
        }, this.intervalMilliSeconds);

        store.commit("setIntervalName", intervalName);
      } else {
        this.performLogout();
      }
    } catch (error) {
      this.performLogout();
    }
  }
}

export default AuthProcessor.instance;
