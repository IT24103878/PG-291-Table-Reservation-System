import axios from "axios";
import { API_CONFIG } from "../utility/config";

const RESERVATIONS_URL = `${API_CONFIG.API_BASE_URL}/reservations`;

export const createReservation = async (reservation) => {
  const response = await axios.post(RESERVATIONS_URL, reservation);
  return response.data;
};

export const getReservations = async () => {
  const response = await axios.get(RESERVATIONS_URL);
  return response.data;
};

export const markReservationComplete = async (id) => {
  const response = await axios.patch(`${RESERVATIONS_URL}/${id}`);
  return response.data;
};
