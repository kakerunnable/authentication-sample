import {User} from "@/infrastructure/auth/user";

const state = {
  isAuthenticated: false,
  currentUser: new User(),
  intervalName: ""
};

const getters = {
  getIsAuthenticated(state: any) {
    return state.isAuthenticated;
  },
  getCurrentUser(state: any): User {
    return state.currentUser;
  },
  getIntervalName(state: any) {
    return state.intervalName;
  }
};

const mutations = {
  setIsAuthenticated(state: any, isAuthenticated: any) {
    state.isAuthenticated = isAuthenticated;
  },
  setCurrentUser(state: any, user: User) {
    state.currentUser = user;
  },
  setIntervalName(state: any, intervalName: any) {
    state.intervalName = intervalName;
  },
  clearCurrentUser(state: any) {
    state.currentUser = new User();
  }
};

const actions = {
  // @ts-ignore
  async login({ commit, dispatch }, loginData) {},
  // @ts-ignore
  async getCurrentUser({ commit, dispatch }) {},
  // @ts-ignore
  async refresh({ commit, dispatch, getters }) {},
  // @ts-ignore
  async logout({ commit, dispatch, getters }) {}
};

export default {
  namespaced: false,
  state,
  getters,
  mutations,
  actions
};
