import axios from "axios";
import { API_CONFIG } from "../utility/config";

const TABLES_URL = `${API_CONFIG.API_BASE_URL}/tables`;

export const getTables = async () => {
  const response = await axios.get(TABLES_URL);
  return response.data;
};

export const getTableById = async (id) => {
  const response = await axios.get(`${TABLES_URL}/${id}`);
  return response.data;
};

export const createTable = async (table) => {
  const response = await axios.post(TABLES_URL, table);
  return response.data;
};

export const updateTable = async (id, table) => {
  const response = await axios.put(`${TABLES_URL}/${id}`, table);
  return response.data;
};

export const deleteTable = async (id) => {
  await axios.delete(`${TABLES_URL}/${id}`);
};

export const isTableAvailable = async () => {
  const response = await axios.get(`${TABLES_URL}/available`);
  return response.data;
};

export const getAvailableTableId = async () => {
  const response = await axios.get(`${TABLES_URL}/available/id`);
  return response.data;
};
