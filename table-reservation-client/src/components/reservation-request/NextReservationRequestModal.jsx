import { useEffect, useState } from "react";
import * as foodService from "../../services/foodService";

const NextRequestModal = ({
  showModal,
  setShowModal,
  nextRequest,
  customers,
  onProcessRequest,
  isTableAvailable,
}) => {
  const [foods, setFoods] = useState([]);
  const [selectedFoods, setSelectedFoods] = useState([]);
  const [showFoodSelection, setShowFoodSelection] = useState(false);
  const [selectedFoodId, setSelectedFoodId] = useState("");
  const [quantity, setQuantity] = useState(1);

  useEffect(() => {
    if (showModal) {
      fetchFoods();
    }
  }, [showModal]);

  const fetchFoods = async () => {
    try {
      const foods = await foodService.getFoods();
      setFoods(foods);
    } catch (error) {
      console.error("Error fetching foods:", error);
    }
  };

  const handleAddFood = () => {
    if (!selectedFoodId || quantity < 1) return;

    const foodToAdd = foods.find((food) => food.id === selectedFoodId);
    if (!foodToAdd) return;

    setSelectedFoods((prev) => [
      ...prev,
      {
        ...foodToAdd,
        quantity: quantity,
      },
    ]);

    // Reset selection
    setSelectedFoodId("");
    setQuantity(1);
    setShowFoodSelection(false);
  };

  const handleRemoveFood = (foodId) => {
    setSelectedFoods((prev) => prev.filter((food) => food.id !== foodId));
  };

  const handleQuantityChange = (foodId, newQuantity) => {
    setSelectedFoods((prev) =>
      prev.map((food) =>
        food.id === foodId
          ? { ...food, quantity: Math.max(1, newQuantity) }
          : food
      )
    );
  };

  const handleProcess = () => {
    onProcessRequest({
      items: selectedFoods,
      totalPrice: calculateTotal(),
    });
    setSelectedFoods([]);
  };

  if (!showModal || !nextRequest) return null;

  const getCustomerName = (customerId) => {
    const customer = customers.find((c) => c.id === customerId);
    return customer ? customer.name : "Unknown Customer";
  };

  const formatDateTime = (isoString) => {
    return new Date(isoString).toLocaleString();
  };

  const calculateTotal = () => {
    return selectedFoods
      .reduce((sum, item) => sum + item.price * item.quantity, 0)
      .toFixed(2);
  };

  return (
    <div className="fixed inset-0 backdrop-blur-sm flex items-center justify-center p-4 z-50">
      <div className="bg-white rounded-lg shadow-xl w-full max-w-5xl max-h-[90vh] overflow-y-auto">
        <div className="p-6">
          <h2 className="text-xl font-semibold mb-4">
            Next Reservation Request
          </h2>

          <div className="space-y-4 mb-6">
            <div>
              <h3 className="font-medium text-gray-700">Customer</h3>
              <p className="text-gray-900">
                {getCustomerName(nextRequest.customerId)}
              </p>
            </div>

            <div>
              <h3 className="font-medium text-gray-700">Request Time</h3>
              <p className="text-gray-900">
                {formatDateTime(nextRequest.dateTime)}
              </p>
            </div>

            <div>
              <h3 className="font-medium text-gray-700">Guest Count</h3>
              <p className="text-gray-900">{nextRequest.count}</p>
            </div>
          </div>

          {isTableAvailable && (
            <div className="mb-6">
              <div className="flex justify-between items-center mb-4">
                <h3 className="font-medium text-gray-700">Food Items</h3>
                <button
                  onClick={() => setShowFoodSelection(true)}
                  className="px-3 py-1 bg-blue-600 text-white rounded-md hover:bg-blue-700 text-sm"
                >
                  + Add Food
                </button>
              </div>

              {showFoodSelection && (
                <div className="bg-gray-50 p-4 rounded-lg mb-4">
                  <div className="grid grid-cols-1 gap-4">
                    <div>
                      <label className="block text-sm font-medium text-gray-700 mb-1">
                        Select Food Item
                      </label>
                      <select
                        value={selectedFoodId}
                        onChange={(e) => setSelectedFoodId(e.target.value)}
                        className="w-full px-3 py-2 border rounded-md"
                      >
                        <option value="">Select a food item</option>
                        {foods.map((food) => (
                          <option key={food.id} value={food.id}>
                            {food.name} (${food.price.toFixed(2)})
                          </option>
                        ))}
                      </select>
                    </div>

                    <div>
                      <label className="block text-sm font-medium text-gray-700 mb-1">
                        Quantity
                      </label>
                      <input
                        type="number"
                        min="1"
                        value={quantity}
                        onChange={(e) =>
                          setQuantity(parseInt(e.target.value) || 1)
                        }
                        className="w-full px-3 py-2 border rounded-md"
                      />
                    </div>

                    <div className="flex justify-end space-x-2">
                      <button
                        onClick={() => setShowFoodSelection(false)}
                        className="px-3 py-1 border border-gray-300 rounded-md text-gray-700 hover:bg-gray-100 text-sm"
                      >
                        Cancel
                      </button>
                      <button
                        onClick={handleAddFood}
                        disabled={!selectedFoodId}
                        className="px-3 py-1 bg-green-600 text-white rounded-md hover:bg-green-700 text-sm disabled:bg-gray-400"
                      >
                        Add Item
                      </button>
                    </div>
                  </div>
                </div>
              )}

              {selectedFoods.length > 0 ? (
                <div className="border rounded-lg overflow-hidden">
                  <table className="min-w-full divide-y divide-gray-200">
                    <thead className="bg-gray-50">
                      <tr>
                        <th className="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">
                          Item
                        </th>
                        <th className="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">
                          Price
                        </th>
                        <th className="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">
                          Qty
                        </th>
                        <th className="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">
                          Total
                        </th>
                        <th className="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase">
                          Action
                        </th>
                      </tr>
                    </thead>
                    <tbody className="divide-y divide-gray-200">
                      {selectedFoods.map((food) => (
                        <tr key={food.id}>
                          <td className="px-4 py-2">{food.name}</td>
                          <td className="px-4 py-2">{food.price.toFixed(2)}</td>
                          <td className="px-4 py-2">
                            <input
                              type="number"
                              min="1"
                              value={food.quantity}
                              onChange={(e) =>
                                handleQuantityChange(
                                  food.id,
                                  parseInt(e.target.value) || 1
                                )
                              }
                              className="w-16 px-2 py-1 border rounded"
                            />
                          </td>
                          <td className="px-4 py-2">
                            {(food.price * food.quantity).toFixed(2)}
                          </td>
                          <td className="px-4 py-2">
                            <button
                              onClick={() => handleRemoveFood(food.id)}
                              className="text-red-600 hover:text-red-800"
                            >
                              Remove
                            </button>
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                  <div className="px-4 py-3 bg-gray-50 text-right font-medium">
                    Total: {calculateTotal()}
                  </div>
                </div>
              ) : (
                <p className="text-gray-500 text-center py-4">
                  No food items selected
                </p>
              )}
            </div>
          )}

          <div className="mt-6 flex justify-end space-x-3">
            <button
              type="button"
              onClick={() => {
                setShowModal(false);
                setSelectedFoods([]);
              }}
              className="px-4 py-2 border border-gray-300 rounded-md text-gray-700 hover:bg-gray-50"
            >
              Close
            </button>
            {isTableAvailable && selectedFoods.length > 0 && (
              <button
                type="button"
                onClick={handleProcess}
                className="px-4 py-2 bg-green-600 text-white rounded-md hover:bg-green-700"
              >
                Process Request
              </button>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default NextRequestModal;
