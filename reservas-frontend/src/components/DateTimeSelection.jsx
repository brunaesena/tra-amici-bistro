import React, { useState } from 'react';
import './DateTimeSelection.css';

const DateTimeSelection = () => {
  const [selectedDate, setSelectedDate] = useState(null);
  const [selectedTime, setSelectedTime] = useState(null);
  const [isReserved, setIsReserved] = useState(false);
  const [name, setName] = useState('');
  const [phone, setPhone] = useState('');

  const dates = [
    { date: '20 de Abril (Sábado)', times: ['12:00', '13:30', '15:00'] },
    { date: '21 de Abril (Domingo)', times: ['11:00', '13:00', '14:30'] },
    { date: '22 de Abril (Segunda)', times: ['18:00', '19:30', '21:00'] }
  ];

  if (isReserved) {
    return (
      <div className="datetime-container">
        <h2>Reserva Confirmada!</h2>
        <div className="reservation-info">
          <p><span>Nome:</span> {name}</p>
          <p><span>Telefone:</span> {phone}</p>
          <p><span>Data:</span> {selectedDate} às {selectedTime}</p>
        </div>
        <button 
          className="reserve-button" 
          onClick={() => setIsReserved(false)}
        >
          Fazer Nova Reserva
        </button>
      </div>
    );
  }

  return (
    <div className="datetime-container">
      <h2>Selecione uma data e horário</h2>

      {/* Campos adicionados - Estilo IDÊNTICO ao existente */}
      <div className="input-field">
        <label htmlFor="name">Nome Completo</label>
        <input
          type="text"
          id="name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder="Digite seu nome"
          required
          className="datetime-input"
        />
      </div>

      <div className="input-field">
        <label htmlFor="phone">Telefone</label>
        <input
          type="tel"
          id="phone"
          value={phone}
          onChange={(e) => setPhone(e.target.value)}
          placeholder="(00) 00000-0000"
          required
          className="datetime-input"
        />
      </div>

      <div className="cards-wrapper">
        {dates.map((item, index) => (
          <div 
            className={`datetime-card ${selectedDate === item.date ? 'selected' : ''}`} 
            key={index}
            onClick={() => setSelectedDate(item.date)}
          >
            <h3>{item.date}</h3>
            <div className="times">
              {item.times.map((time, i) => (
                <span 
                  className={`time-slot ${selectedTime === time ? 'selected' : ''}`}
                  key={i}
                  onClick={(e) => {
                    e.stopPropagation();
                    setSelectedTime(time);
                  }}
                >
                  {time}
                </span>
              ))}
            </div>
          </div>
        ))}
      </div>

      {selectedDate && selectedTime && name && phone && (
        <button 
          className="reserve-button"
          onClick={() => setIsReserved(true)}
        >
          Confirmar Reserva
        </button>
      )}
    </div>
  );
};

export default DateTimeSelection;