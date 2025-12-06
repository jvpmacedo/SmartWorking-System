import React, { useEffect, useState } from "react";
import api from "../services/api";

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
    <div>
      <h2>Dashboard</h2>
      <div>
        {espacos.map((espaco) => (
          <div key={espaco.id} style={{ border: "1px solid black", margin: "10px", padding: "10px" }}>
            <h3>{espaco.nome}</h3>
            <p>{espaco.tipo}</p>
            <p>R$ {espaco.precoHora}/hora</p>
            {espaco.fotoBase64 && <img src={espaco.fotoBase64} alt={espaco.nome} style={{ maxWidth: "200px" }} />}
            <button>Reservar</button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Dashboard;
