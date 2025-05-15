import { Route, Routes } from "react-router-dom";
import ReservationRequestsView from "./components/reservation-request/ReservationRequestsView";
import ReservationsView from "./components/reservation/ReservationsView";

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
        </Routes>
      </main>
    </div>
  );
}

export default App;
