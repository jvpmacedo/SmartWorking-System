import React from "react";
import { Link, useNavigate } from "react-router-dom";
import styles from "./Navbar.module.css";

const Navbar = () => {
  const user = JSON.parse(localStorage.getItem("user"));
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("user");
    navigate("/login");
  };

  return (
    <nav className={styles.navbar}>
      <div className={styles.navLinks}>
        <Link to="/">Dashboard</Link>
        {user && <Link to="/admin">Admin</Link>}
      </div>
      <div>
        {user ? (
          <button onClick={handleLogout}>Sair</button>
        ) : (
          <div className={styles.navLinks}>
            <Link to="/login">Login</Link>
            <Link to="/signup">Cadastro</Link>
          </div>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
