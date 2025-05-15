import { useEffect } from "react";

const FoodModal = ({
  showModal,
  setShowModal,
  currentFood,
  setCurrentFood,
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
            {isEditing ? "Edit Food Item" : "Add New Food Item"}
          </h2>

          <form onSubmit={handleSubmit}>
            <div className="mb-4">
              <label className="block text-gray-700 mb-2">Food Name*</label>
              <input
                type="text"
                value={currentFood.name}
                onChange={(e) =>
                  setCurrentFood({ ...currentFood, name: e.target.value })
                }
                className="w-full px-3 py-2 border rounded-md"
                required
              />
            </div>

            <div className="mb-4">
              <label className="block text-gray-700 mb-2">Image URL</label>
              <input
                type="text"
                value={currentFood.imageUrl}
                onChange={(e) =>
                  setCurrentFood({ ...currentFood, imageUrl: e.target.value })
                }
                className="w-full px-3 py-2 border rounded-md"
              />
            </div>

            <div className="mb-4">
              <label className="block text-gray-700 mb-2">Description*</label>
              <textarea
                value={currentFood.description}
                onChange={(e) =>
                  setCurrentFood({
                    ...currentFood,
                    description: e.target.value,
                  })
                }
                className="w-full px-3 py-2 border rounded-md"
                rows="3"
                required
              />
            </div>

            <div className="mb-6">
              <label className="block text-gray-700 mb-2">Price*</label>
              <div className="relative">
                <input
                  type="number"
                  value={currentFood.price}
                  onChange={(e) =>
                    setCurrentFood({
                      ...currentFood,
                      price: parseFloat(e.target.value),
                    })
                  }
                  className="w-full px-3 py-2 border rounded-md"
                  required
                />
              </div>
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
                {isEditing ? "Update Item" : "Add Item"}
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default FoodModal;
