import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api";

function Register() {
const [firstName, setFirstName] = useState("");
const [lastName, setLastName] = useState("");
const [email, setEmail] = useState("");
const [password, setPassword] = useState("");
const [error, setError] = useState("");
const [success, setSuccess] = useState("");
const navigate = useNavigate();

const handleRegister = async (e) => {
e.preventDefault();
setError("");
setSuccess("");

 
try {
  await api.post("/api/auth/register", {
    firstName,
    lastName,
    email,
    password,
    role: "CUSTOMER",
  });

  setSuccess("Account created successfully! Redirecting to login...");

  setTimeout(() => {
    navigate("/login");
  }, 1500);
} catch (error) {
  if (error.response?.status === 409) {
    setError("An account with this email already exists.");
  } else if (error.response?.data) {
    setError(
      typeof error.response.data === "string"
        ? error.response.data
        : "Registration failed. Please check your information."
    );
  } else {
    setError("Registration failed. Please try again.");
  }
}
 

};

return ( <div className="auth-page"> <form className="auth-form" onSubmit={handleRegister}> <h2>Create Account</h2>

 
    {error && <p className="auth-error">{error}</p>}
    {success && <p className="auth-success">{success}</p>}

    <label>First Name</label>
    <input
      type="text"
      value={firstName}
      onChange={(e) => setFirstName(e.target.value)}
      placeholder="Enter your first name"
      required
    />

    <label>Last Name</label>
    <input
      type="text"
      value={lastName}
      onChange={(e) => setLastName(e.target.value)}
      placeholder="Enter your last name"
      required
    />

    <label>Email</label>
    <input
      type="email"
      value={email}
      onChange={(e) => setEmail(e.target.value)}
      placeholder="Enter your email"
      required
    />

    <label>Password</label>
    <input
      type="password"
      value={password}
      onChange={(e) => setPassword(e.target.value)}
      placeholder="Create a password"
      required
    />

    <button type="submit">Create Account</button>

    <p className="auth-link">
      Already have an account?{" "}
      <span onClick={() => navigate("/login")}>Login</span>
    </p>
  </form>
</div>

);
}

export default Register;
