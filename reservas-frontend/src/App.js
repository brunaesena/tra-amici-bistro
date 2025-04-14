// App.js
import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import AppRoutes from './Routes';

const App = () => {
  return (
    <BrowserRouter>
      <AppRoutes />
    </BrowserRouter>
  );
};

export default App;
