import React from 'react';
import { Link } from 'react-router-dom';
import './SignupForm.css';

const SignupForm = () => {
  return (
    <div className="container">
      <div className="brand">Tra <span>Amici Bistrô</span></div>
      <h2>Let's get you started</h2>
      <form>
        <label htmlFor="email">Email</label>
        <input type="email" id="email" placeholder="Digite seu email" required />

        <label htmlFor="fullname">Nome Completo</label>
        <input type="text" id="fullname" placeholder="Digite seu nome" required />

        <label htmlFor="senha">Senha</label>
        <input type="password" id="senha" placeholder="Digite sua senha" required />

        <button type="submit">Sign Up</button>
      </form>
      <div className="signin">
        Already a user? <Link to="/login">Sign in</Link>
      </div>
    </div>
  );
};

export default SignupForm;