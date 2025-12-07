import React, { useState } from "react";
import api from "../services/api";
import styles from "./Admin.module.css";

const Admin = () => {
  const [nome, setNome] = useState("");
  const [tipo, setTipo] = useState("Mesa");
  const [precoHora, setPrecoHora] = useState("");
  const [fotoBase64, setFotoBase64] = useState("");
  const [endereco, setEndereco] = useState("");

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setFotoBase64(reader.result);
      };
      reader.readAsDataURL(file);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post("/espacos", {
        nome,
        tipo,
        precoHora: parseFloat(precoHora),
        fotoBase64,
        endereco,
      });
      alert("Espaço cadastrado com sucesso!");
    } catch (error) {
      alert("Erro ao cadastrar espaço.");
    }
  };

  return (
    <div className={styles.container}>
      <form className={styles.form} onSubmit={handleSubmit}>
        <h2>Admin - Cadastrar Novo Espaço</h2>
        <input
          type="text"
          value={nome}
          onChange={(e) => setNome(e.target.value)}
          placeholder="Nome do Espaço"
          required
        />
        <input
          type="text"
          value={endereco}
          onChange={(e) => setEndereco(e.target.value)}
          placeholder="Endereço"
          required
        />
        <select value={tipo} onChange={(e) => setTipo(e.target.value)}>
          <option value="Mesa">Mesa</option>
          <option value="Sala">Sala</option>
        </select>
        <input
          type="number"
          value={precoHora}
          onChange={(e) => setPrecoHora(e.target.value)}
          placeholder="Preço por Hora"
          required
        />
        <input type="file" accept="image/*" onChange={handleFileChange} />
        <button type="submit">Cadastrar</button>
      </form>
    </div>
  );
};

export default Admin;
