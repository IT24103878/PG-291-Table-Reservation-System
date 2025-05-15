// src/components/Navbar.jsx
import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <nav className="bg-blue-600 text-white shadow-md">
      <div className="max-w-7xl mx-auto px-4">
        <div className="flex items-center justify-between h-16">
          {/* Logo */}
          <div className="flex-shrink-0">
            <Link to="/" className="text-xl font-bold">
              Table Reservation System
            </Link>
          </div>

          {/* Navigation Buttons */}
          <div className="flex space-x-4">
            <NavButton to="/" text="Customers" />
            <NavButton to="/foods" text="Food" />
            <NavButton to="/tables" text="Tables" />
            <NavButton to="/waiters" text="Waiters" />
            <NavButton to="/reservation-requests" text="Reservation Requests" />
            <NavButton to="/reservations" text="Reservations" />
          </div>
        </div>
      </div>
    </nav>
  );
};

const NavButton = ({ to, text }) => (
  <Link
    to={to}
    className="px-3 py-2 rounded-md text-sm font-medium hover:bg-blue-700 transition duration-300"
  >
    {text}
  </Link>
);

export default Navbar;
