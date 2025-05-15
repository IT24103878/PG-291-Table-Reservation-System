import axios from "axios";
import { API_CONFIG } from "../utility/config";

const FOODS_URL = `${API_CONFIG.API_BASE_URL}/foods`;

export const getFoods = async () => {
  const response = await axios.get(FOODS_URL);
  return response.data;
};

export const getFoodById = async (id) => {
  const response = await axios.get(`${FOODS_URL}/${id}`);
  return response.data;
};

export const createFood = async (food) => {
  const response = await axios.post(FOODS_URL, food);
  return response.data;
};

export const updateFood = async (id, food) => {
  const response = await axios.put(`${FOODS_URL}/${id}`, food);
  return response.data;
};

export const deleteFood = async (id) => {
  await axios.delete(`${FOODS_URL}/${id}`);
};
