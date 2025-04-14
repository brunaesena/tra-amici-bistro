import React from 'react';
import { Routes, Route } from 'react-router-dom';
import BistroCadastro from './components/Cadastro';
import SignupForm from './components/SignupForm';
import DateTimeSelection from './components/DateTimeSelection';
import Login from './components/Login'; 

const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<BistroCadastro />} />
      <Route path="/signup-email" element={<SignupForm />} />
      <Route path="/select-date" element={<DateTimeSelection />} />
      <Route path="/login" element={<Login />} /> {/* Nova rota */}
    </Routes>
  );
};

export default AppRoutes;