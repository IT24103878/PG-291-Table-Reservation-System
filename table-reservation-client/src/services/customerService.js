import axios from "axios";
import { API_CONFIG } from "../utility/config";

const CUSTOMERS_URL = `${API_CONFIG.API_BASE_URL}/customers`;

export const getCustomers = async () => {
  const response = await axios.get(CUSTOMERS_URL);
  return response.data;
};

export const getCustomerById = async (id) => {
  const response = await axios.get(`${CUSTOMERS_URL}/${id}`);
  return response.data;
};

export const createCustomer = async (customer) => {
  const response = await axios.post(CUSTOMERS_URL, customer);
  return response.data;
};

export const updateCustomer = async (id, customer) => {
  const response = await axios.put(`${CUSTOMERS_URL}/${id}`, customer);
  return response.data;
};

export const deleteCustomer = async (id) => {
  await axios.delete(`${CUSTOMERS_URL}/${id}`);
};
