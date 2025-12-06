import React, { useEffect, useState } from "react";
import api from "../services/api";
import styles from "./Dashboard.module.css";

const Dashboard = () => {
  const [espacos, setEspacos] = useState([]);

  useEffect(() => {
    const fetchEspacos = async () => {
      try {
        const response = await api.get("/espacos");
        setEspacos(response.data);
      } catch (error) {
        console.error("Erro ao buscar espaços:", error);
      }
    };
    fetchEspacos();
  }, []);

  return (
    <div className={styles.container}>
      <h2>Dashboard</h2>
      <div className={styles.grid}>
        {espacos.map((espaco) => (
          <div key={espaco.id} className={styles.card}>
            {espaco.fotoBase64 && <img src={espaco.fotoBase64} alt={espaco.nome} />}
            <h3>{espaco.nome}</h3>
            <p>{espaco.tipo}</p>
            <p>R$ {espaco.precoHora}/hora</p>
            <button>Reservar</button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Dashboard;
