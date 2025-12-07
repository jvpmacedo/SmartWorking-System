import React, { useEffect, useState } from "react";
import api from "../services/api";
import styles from "./MinhasReservas.module.css";

const MinhasReservas = () => {
  const [reservas, setReservas] = useState([]);
  const user = JSON.parse(localStorage.getItem("user"));

  useEffect(() => {
    const fetchReservas = async () => {
      try {
        const response = await api.get(`/reservas/usuario/${user.id}`);
        setReservas(response.data);
      } catch (error) {
        console.error("Erro ao buscar reservas:", error);
      }
    };
    if (user) {
      fetchReservas();
    }
  }, [user]);

  const handleCancel = (reservaId) => {
    // Implementar a lógica de cancelamento de reserva
    console.log("Cancelar reserva:", reservaId);
  };

  return (
    <div className={styles.container}>
      <h2>Minhas Reservas</h2>
      <div className={styles.grid}>
        {reservas.map((reserva) => (
          <div key={reserva.id} className={styles.card}>
            <h3>{reserva.espaco.nome}</h3>
            <p>{reserva.espaco.endereco}</p>
            <p>Início: {new Date(reserva.inicio).toLocaleString()}</p>
            <p>Fim: {new Date(reserva.fim).toLocaleString()}</p>
            <button onClick={() => handleCancel(reserva.id)}>Cancelar</button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default MinhasReservas;
