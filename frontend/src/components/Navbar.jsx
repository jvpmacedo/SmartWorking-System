import React from "react";
import { Link, useNavigate } from "react-router-dom";

const Navbar = () => {
  const user = JSON.parse(localStorage.getItem("user"));
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("user");
    navigate("/login");
  };

  return (
    <nav>
      <Link to="/">Dashboard</Link>
      {user ? (
        <>
          <Link to="/admin">Admin</Link>
          <button onClick={handleLogout}>Sair</button>
        </>
      ) : (
        <>
          <Link to="/login">Login</Link>
          <Link to="/signup">Cadastro</Link>
        </>
      )}
    </nav>
  );
};

export default Navbar;
