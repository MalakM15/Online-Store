import { useNavigate } from "react-router-dom";

function Navbar() {
  const navigate = useNavigate();

  const email = sessionStorage.getItem("email");

  function handleLogout() {
    sessionStorage.removeItem("email");
    sessionStorage.removeItem("password");

    navigate("/login");
  }

  return (
    <nav className="navbar">
      <div className="logo">My Store</div>

      <div className="nav-links">
        <a href="/">Products</a>
        <a href="/cart">My Cart</a>
        <a href="/orders">Orders</a>

        {email ? (
          <button onClick={handleLogout}>Logout</button>
        ) : (
          <a href="/login">Login</a>
        )}
      </div>
    </nav>
  );
}

export default Navbar;