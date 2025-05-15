import { useEffect } from "react";

const TableModal = ({
  showModal,
  setShowModal,
  currentTable,
  setCurrentTable,
  handleSubmit,
  isEditing,
  waiters,
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
            {isEditing ? "Edit Table" : "Add New Table"}
          </h2>

          <form onSubmit={handleSubmit}>
            <div className="mb-4">
              <label className="block text-gray-700 mb-2">Table Number*</label>
              <input
                type="text"
                value={currentTable.number}
                onChange={(e) =>
                  setCurrentTable({ ...currentTable, number: e.target.value })
                }
                className="w-full px-3 py-2 border rounded-md"
                required
              />
            </div>

            <div className="mb-4">
              <label className="block text-gray-700 mb-2">Capacity*</label>
              <input
                type="text"
                value={currentTable.capacity}
                onChange={(e) =>
                  setCurrentTable({
                    ...currentTable,
                    capacity: e.target.value,
                  })
                }
                className="w-full px-3 py-2 border rounded-md"
                required
              />
            </div>

            <div className="mb-4">
              <label className="block text-gray-700 mb-2">Status</label>
              <select
                value={currentTable.available}
                onChange={(e) =>
                  setCurrentTable({
                    ...currentTable,
                    available: e.target.value === "true",
                  })
                }
                className="w-full px-3 py-2 border rounded-md"
              >
                <option value={true}>Available</option>
                <option value={false}>Occupied</option>
              </select>
            </div>

            <div className="mb-6">
              <label className="block text-gray-700 mb-2">
                Assigned Waiter*
              </label>
              <select
                value={currentTable.waiterId || ""}
                onChange={(e) =>
                  setCurrentTable({
                    ...currentTable,
                    waiterId: e.target.value ? e.target.value : null,
                  })
                }
                className="w-full px-3 py-2 border rounded-md"
                required
              >
                {waiters.map((waiter) => (
                  <option key={waiter.id} value={waiter.id}>
                    {waiter.name}
                  </option>
                ))}
              </select>
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
                {isEditing ? "Update Table" : "Add Table"}
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default TableModal;
