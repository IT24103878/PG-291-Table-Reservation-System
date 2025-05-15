import axios from "axios";
import { API_CONFIG } from "../utility/config";

const RESERVATION_REQUESTS_URL = `${API_CONFIG.API_BASE_URL}/reservation/requests`;

export const getReservationRequests = async () => {
  const response = await axios.get(RESERVATION_REQUESTS_URL);
  return response.data;
};

export const getNextRequest = async () => {
  const response = await axios.get(`${RESERVATION_REQUESTS_URL}/next`);
  return response.data;
};

export const processNextRequest = async () => {
  const response = await axios.post(`${RESERVATION_REQUESTS_URL}/process-next`);
  return response.data;
};

export const createReservationRequest = async (request) => {
  const response = await axios.post(RESERVATION_REQUESTS_URL, request);
  return response.data;
};

export const deleteReservationRequest = async (id) => {
  await axios.delete(`${RESERVATION_REQUESTS_URL}/${id}`);
};
