import { useState, useEffect } from "react";
import TableModal from "./TableModal";
import * as tableService from "../../services/tableService";
import * as waiterService from "../../services/waiterService";
import { useNavigate } from "react-router-dom";

const TablesView = () => {
  const [tables, setTables] = useState([]);
  const [waiters, setWaiters] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [currentTable, setCurrentTable] = useState({
    number: "",
    capacity: "",
    available: true,
    waiterId: null,
  });
  const [isEditing, setIsEditing] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    fetchTables();
    fetchWaiters();
  }, []);

  const fetchTables = async () => {
    try {
      const data = await tableService.getTables();
      setTables(data);
    } catch (error) {
      console.error("Error fetching tables:", error);
    }
  };

  const fetchWaiters = async () => {
    try {
      const data = await waiterService.getWaiters();
      setWaiters(data.filter((waiter) => waiter.available));
    } catch (error) {
      console.error("Error fetching waiters:", error);
    }
  };

  const handleCreateClick = () => {
    if (waiters.length === 0) {
      alert("No available waiters. Please add waiters first.");
      navigate("/waiters");
      return;
    }
    setCurrentTable({
      number: "",
      capacity: 0,
      available: true,
      waiterId: waiters[0]?.id || null,
    });
    setIsEditing(false);
    setShowModal(true);
  };

  const handleEditClick = (table) => {
    setCurrentTable(table);
    setIsEditing(true);
    setShowModal(true);
  };

  const handleDelete = async (id) => {
    try {
      await tableService.deleteTable(id);
      fetchTables();
    } catch (error) {
      console.error("Error deleting table:", error);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (isEditing) {
        await tableService.updateTable(currentTable.id, currentTable);
      } else {
        await tableService.createTable(currentTable);
      }
      setShowModal(false);
      fetchTables();
    } catch (error) {
      console.error("Error saving table:", error);
    }
  };

  const getWaiterName = (waiterId) => {
    const waiter = waiters.find((w) => w.id === waiterId);
    return waiter ? waiter.name : "Unassigned";
  };

  return (
    <div className="container mx-auto p-4">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">Restaurant Tables</h1>
        <button
          onClick={handleCreateClick}
          className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
        >
          Add Table
        </button>
      </div>

      <div className="bg-white rounded-lg shadow overflow-hidden">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Table #
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Capacity
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Status
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Assigned Waiter
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Actions
              </th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {tables.map((table) => (
              <tr key={table.id}>
                <td className="px-6 py-4 whitespace-nowrap">
                  <div className="text-sm font-medium text-gray-900">
                    {table.number}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                  <div className="text-sm text-gray-500">{table.capacity}</div>
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                  <span
                    className={`px-2 inline-flex text-xs leading-5 font-semibold rounded-full 
                    ${
                      table.available
                        ? "bg-green-100 text-green-800"
                        : "bg-red-100 text-red-800"
                    }`}
                  >
                    {table.available ? "Available" : "Occupied"}
                  </span>
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                  <div className="text-sm text-gray-500">
                    {getWaiterName(table.waiterId)}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">
                  <button
                    onClick={() => handleEditClick(table)}
                    className="text-blue-600 hover:text-blue-900 mr-3"
                  >
                    Edit
                  </button>
                  <button
                    onClick={() => handleDelete(table.id)}
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

      <TableModal
        showModal={showModal}
        setShowModal={setShowModal}
        currentTable={currentTable}
        setCurrentTable={setCurrentTable}
        handleSubmit={handleSubmit}
        isEditing={isEditing}
        waiters={waiters}
      />
    </div>
  );
};

export default TablesView;
