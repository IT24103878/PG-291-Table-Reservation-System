import { useState, useEffect } from "react";
import WaiterModal from "./WaiterModal";
import * as waiterService from "../../services/waiterService";

const WaitersView = () => {
  const [waiters, setWaiters] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [currentWaiter, setCurrentWaiter] = useState({
    name: "",
    nic: "",
    available: false,
  });
  const [isEditing, setIsEditing] = useState(false);

  useEffect(() => {
    fetchWaiters();
  }, []);

  const fetchWaiters = async () => {
    try {
      const data = await waiterService.getWaiters();
      setWaiters(data);
    } catch (error) {
      console.error("Error fetching waiters:", error);
    }
  };

  const handleCreateClick = () => {
    setCurrentWaiter({ name: "", nic: "", available: false });
    setIsEditing(false);
    setShowModal(true);
  };

  const handleEditClick = (waiter) => {
    setCurrentWaiter(waiter);
    setIsEditing(true);
    setShowModal(true);
  };

  const handleDelete = async (id) => {
    try {
      await waiterService.deleteWaiter(id);
      fetchWaiters();
    } catch (error) {
      console.error("Error deleting waiter:", error);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (isEditing) {
        await waiterService.updateWaiter(currentWaiter.id, currentWaiter);
      } else {
        await waiterService.createWaiter(currentWaiter);
      }
      setShowModal(false);
      fetchWaiters();
    } catch (error) {
      console.error("Error saving waiter:", error);
    }
  };

  return (
    <div className="container mx-auto p-4">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">Waiters</h1>
        <button
          onClick={handleCreateClick}
          className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
        >
          Create Waiter
        </button>
      </div>

      <div className="bg-white rounded-lg shadow overflow-hidden">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Name
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                NIC
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Status
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Actions
              </th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {waiters.map((waiter) => (
              <tr key={waiter.id}>
                <td className="px-6 py-4 whitespace-nowrap">
                  <div className="text-sm font-medium text-gray-900">
                    {waiter.name}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                  <div className="text-sm text-gray-500">{waiter.nic}</div>
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                  <span
                    className={`px-2 inline-flex text-xs leading-5 font-semibold rounded-full 
                    ${
                      waiter.available
                        ? "bg-green-100 text-green-800"
                        : "bg-red-100 text-red-800"
                    }`}
                  >
                    {waiter.available ? "Available" : "Unavailable"}
                  </span>
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">
                  <button
                    onClick={() => handleEditClick(waiter)}
                    className="text-blue-600 hover:text-blue-900 mr-3"
                  >
                    Edit
                  </button>
                  <button
                    onClick={() => handleDelete(waiter.id)}
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

      <WaiterModal
        showModal={showModal}
        setShowModal={setShowModal}
        currentWaiter={currentWaiter}
        setCurrentWaiter={setCurrentWaiter}
        handleSubmit={handleSubmit}
        isEditing={isEditing}
      />
    </div>
  );
};

export default WaitersView;
