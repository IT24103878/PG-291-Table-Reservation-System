import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import ReservationRequestModal from "./ReservationRequestModal";
import * as reservationRequestService from "../../services/reservationRequestService";
import * as customerService from "../../services/customerService";
import * as tableService from "../../services/tableService";
import * as reservationService from "../../services/reservationService";
import NextRequestModal from "./NextReservationRequestModal";

const ReservationRequestsView = () => {
  const [requests, setRequests] = useState([]);
  const [customers, setCustomers] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [showNextRequestModal, setShowNextRequestModal] = useState(false);
  const [currentRequest, setCurrentRequest] = useState({
    customerId: "",
    count: 1,
  });
  const [nextRequest, setNextRequest] = useState(null);
  const [isTableAvailable, setIsTableAvailable] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    fetchRequests();
    fetchCustomers();
  }, []);

  const fetchRequests = async () => {
    try {
      const data = await reservationRequestService.getReservationRequests();
      setRequests(data);
    } catch (error) {
      console.error("Error fetching reservation requests:", error);
    }
  };

  const fetchCustomers = async () => {
    try {
      const data = await customerService.getCustomers();
      setCustomers(data);
    } catch (error) {
      console.error("Error fetching customers:", error);
    }
  };

  const handleCreateClick = () => {
    setCurrentRequest({
      customerId: customers[0]?.id || "",
      count: 1,
    });
    setShowModal(true);
  };

  const handleDelete = async (id) => {
    try {
      await reservationRequestService.deleteReservationRequest(id);
      fetchRequests();
    } catch (error) {
      console.error("Error deleting reservation request:", error);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await reservationRequestService.createReservationRequest(currentRequest);
      setShowModal(false);
      fetchRequests();
    } catch (error) {
      console.error("Error saving reservation request:", error);
    }
  };

  const handleViewNextClick = async () => {
    const isTableAvailable = await tableService.isTableAvailable();
    const next = await reservationRequestService.getNextRequest();
    setIsTableAvailable(isTableAvailable);
    setNextRequest(next);
    setShowNextRequestModal(true);
  };

  const handleProcessRequest = async (order) => {
    try {
      const processedRequest =
        await reservationRequestService.processNextRequest();

      const availableTableId = await tableService.getAvailableTableId();

      console.log({
        ...processedRequest,
        ...order,
        tableId: availableTableId,
      });

      await reservationService.createReservation({
        ...processedRequest,
        ...order,
        tableId: availableTableId,
      });

      navigate("/reservations");
    } catch (error) {
      console.error("Error processing request:", error);
      alert("Error processing request");
    } finally {
      setShowNextRequestModal(false);
      fetchRequests();
    }
  };

  const getCustomerName = (customerId) => {
    const customer = customers.find((c) => c.id === customerId);
    return customer ? customer.name : "Unknown Customer";
  };

  const formatDateTime = (isoString) => {
    return new Date(isoString).toLocaleString();
  };

  return (
    <div className="container mx-auto p-4">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">Reservation Requests</h1>
        <div>
          <button
            onClick={handleCreateClick}
            className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
            disabled={customers.length === 0}
          >
            {customers.length === 0
              ? "No Customers Available"
              : "Create Request"}
          </button>

          {requests && requests.length > 0 && (
            <button
              onClick={handleViewNextClick}
              className="ml-2 px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
            >
              View Next Request
            </button>
          )}
        </div>
      </div>

      <div className="bg-white rounded-lg shadow overflow-hidden">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Customer
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Request Time
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Guest Count
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Actions
              </th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {requests.map((request) => (
              <tr key={request.id}>
                <td className="px-6 py-4 whitespace-nowrap">
                  <div className="text-sm font-medium text-gray-900">
                    {getCustomerName(request.customerId)}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                  <div className="text-sm text-gray-500">
                    {formatDateTime(request.dateTime)}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                  <div className="text-sm text-gray-500">{request.count}</div>
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">
                  <button
                    onClick={() => handleDelete(request.id)}
                    className="text-red-600 hover:text-red-900"
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <ReservationRequestModal
        showModal={showModal}
        setShowModal={setShowModal}
        currentRequest={currentRequest}
        setCurrentRequest={setCurrentRequest}
        handleSubmit={handleSubmit}
        customers={customers}
      />

      <NextRequestModal
        showModal={showNextRequestModal}
        setShowModal={setShowNextRequestModal}
        nextRequest={nextRequest}
        customers={customers}
        onProcessRequest={handleProcessRequest}
        isTableAvailable={isTableAvailable}
      />
    </div>
  );
};

export default ReservationRequestsView;
