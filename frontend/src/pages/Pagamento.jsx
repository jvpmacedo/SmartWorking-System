import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import api from "../services/api";
import styles from "./Pagamento.module.css";

const Pagamento = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { espaco, dataInicio, horasDuracao } = location.state || {};

  const handleSubmit = async (e) => {
    e.preventDefault();
    const user = JSON.parse(localStorage.getItem("user"));

    try {
      await api.post("/reservas", {
        usuario: { id: user.id },
        espaco: { id: espaco.id },
        dataInicio,
        horasDuracao,
      });
      alert("Reserva Confirmada com Sucesso!");
      navigate("/dashboard");
    } catch (error) {
      alert("Erro ao confirmar a reserva.");
    }
  };

  if (!espaco) {
    return <div>Espaço não encontrado.</div>;
  }

  return (
    <div className={styles.container}>
      <form className={styles.form} onSubmit={handleSubmit}>
        <h2>Pagamento</h2>
        <input type="text" placeholder="Número do Cartão" />
        <input type="text" placeholder="Nome no Cartão" />
        <input type="text" placeholder="Validade" />
        <input type="text" placeholder="CVV" />
        <button type="submit">Pagar e Confirmar</button>
      </form>
    </div>
  );
};

export default Pagamento;
