import { useEffect } from "react";

const WaiterModal = ({
  showModal,
  setShowModal,
  currentWaiter,
  setCurrentWaiter,
  handleSubmit,
  isEditing,
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
            {isEditing ? "Edit Waiter" : "Create New Waiter"}
          </h2>

          <form onSubmit={handleSubmit}>
            <div className="mb-4">
              <label className="block text-gray-700 mb-2">Name</label>
              <input
                type="text"
                value={currentWaiter.name || ""}
                onChange={(e) =>
                  setCurrentWaiter({ ...currentWaiter, name: e.target.value })
                }
                className="w-full px-3 py-2 border rounded-md"
                required
              />
            </div>

            <div className="mb-4">
              <label className="block text-gray-700 mb-2">NIC</label>
              <input
                type="text"
                value={currentWaiter.nic || ""}
                onChange={(e) =>
                  setCurrentWaiter({ ...currentWaiter, nic: e.target.value })
                }
                className="w-full px-3 py-2 border rounded-md"
                required
              />
            </div>

            <div className="mb-4 flex items-center">
              <input
                type="checkbox"
                id="available"
                checked={currentWaiter.available || false}
                onChange={(e) =>
                  setCurrentWaiter({
                    ...currentWaiter,
                    available: e.target.checked,
                  })
                }
                className="h-4 w-4 text-blue-600 rounded"
              />
              <label htmlFor="available" className="ml-2 text-gray-700">
                Available
              </label>
            </div>

            <div className="flex justify-end space-x-2">
              <button
                type="button"
                onClick={() => setShowModal(false)}
                className="px-4 py-2 text-gray-600 hover:text-gray-800"
              >
                Cancel
              </button>
              <button
                type="submit"
                className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
              >
                {isEditing ? "Update" : "Create"}
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default WaiterModal;
