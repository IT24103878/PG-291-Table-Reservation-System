import { useState, useEffect } from "react";
import FoodModal from "./FoodModal";
import * as foodService from "../../services/foodService";

const FoodView = () => {
  const [foods, setFoods] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [currentFood, setCurrentFood] = useState({
    name: "",
    imageUrl: "",
    description: "",
    price: 0,
  });
  const [isEditing, setIsEditing] = useState(false);

  useEffect(() => {
    fetchFoods();
  }, []);

  const fetchFoods = async () => {
    try {
      const data = await foodService.getFoods();
      setFoods(data);
    } catch (error) {
      console.error("Error fetching foods:", error);
    }
  };

  const handleCreateClick = () => {
    setCurrentFood({ name: "", imageUrl: "", description: "", price: "" });
    setIsEditing(false);
    setShowModal(true);
  };

  const handleEditClick = (food) => {
    setCurrentFood(food);
    setIsEditing(true);
    setShowModal(true);
  };

  const handleDelete = async (id) => {
    try {
      await foodService.deleteFood(id);
      fetchFoods();
    } catch (error) {
      console.error("Error deleting food:", error);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (isEditing) {
        await foodService.updateFood(currentFood.id, currentFood);
      } else {
        await foodService.createFood(currentFood);
      }
      setShowModal(false);
      fetchFoods();
    } catch (error) {
      console.error("Error saving food:", error);
    }
  };

  return (
    <div className="container mx-auto p-4">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">Food Items</h1>
        <button
          onClick={handleCreateClick}
          className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
        >
          Add Food Item
        </button>
      </div>

      <div className="bg-white rounded-lg shadow overflow-hidden">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Image
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Name
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Description
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Price
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Actions
              </th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {foods.map((food) => (
              <tr key={food.id}>
                <td className="px-6 py-4 whitespace-nowrap">
                  <div className="w-20 h-20 rounded-full overflow-hidden border border-gray-200">
                    <img
                      src={food.imageUrl}
                      alt={food.name}
                      className="w-full h-full object-cover"
                    />
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                  <div className="text-sm font-medium text-gray-900">
                    {food.name}
                  </div>
                </td>
                <td className="px-6 py-4">
                  <div className="text-sm text-gray-500 max-w-xs truncate">
                    {food.description}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                  <div className="text-sm text-gray-500">
                    {food.price.toFixed(2)}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">
                  <button
                    onClick={() => handleEditClick(food)}
                    className="text-blue-600 hover:text-blue-900 mr-3"
                  >
                    Edit
                  </button>
                  <button
                    onClick={() => handleDelete(food.id)}
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

      <FoodModal
        showModal={showModal}
        setShowModal={setShowModal}
        currentFood={currentFood}
        setCurrentFood={setCurrentFood}
        handleSubmit={handleSubmit}
        isEditing={isEditing}
      />
    </div>
  );
};

export default FoodView;
