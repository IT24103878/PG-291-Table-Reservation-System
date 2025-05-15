import axios from "axios";
import { API_CONFIG } from "../utility/config";

const WAITERS_URL = `${API_CONFIG.API_BASE_URL}/waiters`;

export const getWaiters = async () => {
  const response = await axios.get(WAITERS_URL);
  return response.data;
};

export const getWaiterById = async (id) => {
  const response = await axios.get(`${WAITERS_URL}/${id}`);
  return response.data;
};

export const createWaiter = async (waiter) => {
  const response = await axios.post(WAITERS_URL, waiter);
  return response.data;
};

export const updateWaiter = async (id, waiter) => {
  const response = await axios.put(`${WAITERS_URL}/${id}`, waiter);
  return response.data;
};

export const deleteWaiter = async (id) => {
  await axios.delete(`${WAITERS_URL}/${id}`);
};
