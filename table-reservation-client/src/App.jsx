import { Route, Routes } from "react-router-dom";
import ReservationRequestsView from "./components/reservation-request/ReservationRequestsView";
import ReservationsView from "./components/reservation/ReservationsView";
import FoodView from "./components/food/FoodView";
import TablesView from "./components/table/TablesView";
import WaitersView from "./components/waiter/WaitersView";
import CustomersView from "./components/customer/CustomersView";

function App() {
  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar />
      <main className="container mx-auto py-6 px-4">
        <Routes>
          <Route
            path="/reservation-requests"
            element={<ReservationRequestsView />}
          />{" "}
          <Route path="/reservations" element={<ReservationsView />} />
          <Route path="/foods" element={<FoodView />} />
          <Route path="/tables" element={<TablesView />} />
          <Route path="/waiters" element={<WaitersView />} />
          <Route path="/" element={<CustomersView />} />
        </Routes>
      </main>
    </div>
  );
}

export default App;
