import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../services/api";
import styles from "./SignUp.module.css";

const SignUp = () => {
  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [telefone, setTelefone] = useState("");
  const [termsAccepted, setTermsAccepted] = useState(false);
  const navigate = useNavigate();

  const handleSignUp = async (e) => {
    e.preventDefault();
    if (senha.length < 6) {
      alert("A senha deve ter pelo menos 6 caracteres.");
      return;
    }
    if (!termsAccepted) {
      alert("Você deve aceitar os termos e condições.");
      return;
    }
    try {
      await api.post("/usuarios", { nome, email, senha, telefone });
      navigate("/login");
    } catch (error) {
      alert("Falha no cadastro, verifique os dados.");
    }
  };

  return (
    <div className={styles.container}>
      <form className={styles.form} onSubmit={handleSignUp}>
        <h2>Cadastro</h2>
        <input
          type="text"
          value={nome}
          onChange={(e) => setNome(e.target.value)}
          placeholder="Nome"
          required
        />
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          placeholder="Email"
          required
        />
        <input
          type="password"
          value={senha}
          onChange={(e) => setSenha(e.target.value)}
          placeholder="Senha"
          required
        />
        <input
          type="text"
          value={telefone}
          onChange={(e) => setTelefone(e.target.value)}
          placeholder="Telefone"
          required
        />
        <div className={styles.checkboxContainer}>
          <input
            type="checkbox"
            checked={termsAccepted}
            onChange={(e) => setTermsAccepted(e.target.checked)}
          />
          <label>Eu aceito os termos e condições</label>
        </div>
        <button type="submit">Cadastrar</button>
        <p className={styles.link}>
          Já possui uma conta? <Link to="/login">Entre</Link>
        </p>
      </form>
    </div>
  );
};

export default SignUp;
