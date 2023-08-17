import axios from 'axios';

const BASE_URL = '/api'; // Replace with your actual backend URL

const api = {
  signup: async (userData) => {
    return axios.post(`${BASE_URL}/signup`, userData);
  },
  signin: async (credentials) => {
    return axios.post(`${BASE_URL}/signin`, credentials);
  },
  getCategories: async () => {
    return axios.get(`${BASE_URL}/categories`);
  },
  addCategory: async (categoryData) => {
    return axios.post(`${BASE_URL}/category/add`, categoryData);
  },
  getNotesForCategory: async (categoryId) => {
    return axios.get(`${BASE_URL}/categories/${categoryId}/notes`);
  },
  addNoteToCategory: async (categoryId, noteText) => {
    return axios.post(`${BASE_URL}/categories/${categoryId}/notes`, {
      text: noteText,
    });
  },
  deleteNoteFromCategory: async (categoryId, noteId) => {
    return axios.delete(`${BASE_URL}/categories/${categoryId}/notes/${noteId}`);
  },
};

export default api;