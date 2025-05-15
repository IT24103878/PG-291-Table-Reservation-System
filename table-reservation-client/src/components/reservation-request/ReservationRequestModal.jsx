import { useEffect } from "react";

const ReservationRequestModal = ({
  showModal,
  setShowModal,
  currentRequest,
  setCurrentRequest,
  handleSubmit,
  customers,
}) => {
  useEffect(() => {
    if (showModal) {
      document.body.style.overflow = "hidden";
    } else {
      document.body.style.overflow = "unset";
    }
  }, [showModal]);

  if (!showModal) return null;

  return (
    <div className="fixed inset-0 backdrop-blur-sm flex items-center justify-center p-4 z-50">
      <div className="bg-white rounded-lg shadow-xl w-full max-w-md">
        <div className="p-6">
          <h2 className="text-xl font-semibold mb-4">
            Create Reservation Request
          </h2>

          <form onSubmit={handleSubmit}>
            <div className="mb-4">
              <label className="block text-gray-700 mb-2">Customer*</label>
              <select
                value={currentRequest.customerId}
                onChange={(e) =>
                  setCurrentRequest({
                    ...currentRequest,
                    customerId: e.target.value,
                  })
                }
                className="w-full px-3 py-2 border rounded-md"
                required
              >
                {customers.map((customer) => (
                  <option key={customer.id} value={customer.id}>
                    {customer.name}
                  </option>
                ))}
              </select>
            </div>

            <div className="mb-4">
              <label className="block text-gray-700 mb-2">Guest Count*</label>
              <input
                type="number"
                min="1"
                value={currentRequest.count}
                onChange={(e) =>
                  setCurrentRequest({
                    ...currentRequest,
                    count: parseInt(e.target.value) || 1,
                  })
                }
                className="w-full px-3 py-2 border rounded-md"
                required
              />
            </div>

            <div className="flex justify-end space-x-3">
              <button
                type="button"
                onClick={() => setShowModal(false)}
                className="px-4 py-2 border border-gray-300 rounded-md text-gray-700 hover:bg-gray-50"
              >
                Cancel
              </button>
              <button
                type="submit"
                className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
              >
                Create Request
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default ReservationRequestModal;
