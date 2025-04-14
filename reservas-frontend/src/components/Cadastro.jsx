// Cadastro.jsx
import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './Cadastro.module.css';

const BistroCadastro = () => {
  const navigate = useNavigate();

  const handleSignUp = (method) => {
    console.log(`Iniciando cadastro com ${method}`);
    if (method === 'Email') {
      navigate('/signup-email');
    }
  };

  return (
    <div className={styles.container}>
      <h1 className={styles.logo}>Tra Amici Bistro</h1>
      <div className={styles.signupOptions}>
        <button className={styles.button} onClick={() => handleSignUp('Email')}>
          <span className={styles.icon} aria-hidden="true">✉️</span>
          Cadastrar com Email
        </button>
        <button className={styles.button} onClick={() => handleSignUp('Telefone')}>
          <span className={styles.icon} aria-hidden="true">📞</span>
          Cadastrar com Telefone
        </button>
      </div>
      <div className={styles.separator}>ou</div>
      <p className={styles.loginPrompt}>
        Já é um usuário?{' '}
        <a href="/login" className={styles.loginLink}>Entrar</a>
      </p>
      <div className={styles.footerIcons} aria-label="Ícones relacionados a comida e bebida">
        <span className={styles.icon} aria-hidden="true">🍷</span>
        <span className={styles.icon} aria-hidden="true">🍜</span>
        <span className={styles.icon} aria-hidden="true">🥄</span>
        <span className={styles.icon} aria-hidden="true">🍴</span>
      </div>
    </div>
  );
};

export default BistroCadastro;
