import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import styles from "./Reserva.module.css";

const Reserva = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { espaco } = location.state || {};
  const [dataInicio, setDataInicio] = useState("");
  const [horasDuracao, setHorasDuracao] = useState(1);

  const handleSubmit = (e) => {
    e.preventDefault();
    navigate("/pagamento", { state: { espaco, dataInicio, horasDuracao } });
  };

  if (!espaco) {
    return <div>Espaço não encontrado.</div>;
  }

  return (
    <div className={styles.container}>
      <form className={styles.form} onSubmit={handleSubmit}>
        {espaco.fotoBase64 && <img src={espaco.fotoBase64} alt={espaco.nome} className={styles.image} />}
        <h2>Reservar {espaco.nome}</h2>
        <p>{espaco.tipo}</p>
        <p>R$ {espaco.precoHora}/hora</p>
        <input
          type="datetime-local"
          value={dataInicio}
          onChange={(e) => setDataInicio(e.target.value)}
          required
        />
        <input
          type="number"
          value={horasDuracao}
          onChange={(e) => setHorasDuracao(parseInt(e.target.value, 10))}
          min="1"
          required
        />
        <button type="submit">Ir para Pagamento</button>
      </form>
    </div>
  );
};

export default Reserva;
