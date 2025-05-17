import React, { useState } from 'react';
import './DateTimeSelection.css';
import axios from 'axios';
import dayjs from 'dayjs';

const DateTimeSelection = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [people, setPeople] = useState(1);
  const [selectedDate, setSelectedDate] = useState(null);
  const [selectedTime, setSelectedTime] = useState(null);
  const [isReserved, setIsReserved] = useState(false);
  const [reserva, setReserva] = useState(null);

  const dates = [
    { date: '20/04/2024 (Sábado)', times: ['12:00', '13:30', '15:00'] },
    { date: '21/04/2024 (Domingo)', times: ['11:00', '13:00', '14:30'] }
  ];

  const handleReservation = async () => {
    if (!name || !email || !phone || !selectedDate || !selectedTime) {
      alert('Preencha todos os campos!');
      return;
    }
  
    try {
      const dateOnly = selectedDate.split(' ')[0]; // ex: "20/04/2024"
      const [day, month, year] = dateOnly.split('/');
      const [hour, minute] = selectedTime.split(':');
  
      const formattedDateTime = dayjs(`${year}-${month}-${day}T${hour}:${minute}:00`).format('YYYY-MM-DDTHH:mm:ss');
  
      const response = await axios.post('http://localhost:8080/reserva', {
        clienteNome: name,
        clienteEmail: email,
        clienteTelefone: phone,
        quantidadePessoas: people,
        dataHoraReserva: formattedDateTime
      });
  
      console.log('Reserva feita com sucesso:', response.data);
      setReserva(response.data);
      setIsReserved(true);
    } catch (error) {
      console.error('Erro ao fazer reserva:', error);
      alert('Erro ao fazer a reserva. Tente novamente.');
    }
  };

  if (isReserved && reserva) {
    return (
      <div className="datetime-container">
        <div className="reservation-confirmed">
          <h2>✅ Reserva Confirmada!</h2>
          <div className="reservation-details">
            <p>👤 <strong>{reserva.clienteNome}</strong></p>
            <p>📧 <strong>{reserva.clienteEmail}</strong></p>
            <p>📞 <strong>{reserva.clienteTelefone}</strong></p>
            <p>👥 <strong>{reserva.quantidadePessoas} pessoa{reserva.quantidadePessoas !== 1 ? 's' : ''}</strong></p>
            <p>🍽️ Mesa: <strong>{reserva.numeroMesa}</strong></p>
            <p>📅 {selectedDate} às {selectedTime}</p>
            <p>🟢 Status: <strong>{reserva.statusReserva}</strong></p>
          </div>
          <button 
            className="reserve-button"
            onClick={() => {
              setIsReserved(false);
              setReserva(null);
              setName('');
              setEmail('');
              setPhone('');
              setPeople(1);
              setSelectedDate(null);
              setSelectedTime(null);
            }}
          >
            Nova Reserva
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="datetime-container">
      <h2>Faça sua reserva</h2>

      <div className="reservation-form">
        <div className="input-field">
          <label htmlFor="name">Nome Completo</label>
          <input
            type="text"
            id="name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Seu nome completo"
            required
          />
        </div>

        <div className="input-field">
          <label htmlFor="email">E-mail</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="seu@email.com"
            required
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
          />
        </div>

        <div className="input-field">
          <label htmlFor="people">Quantidade de Pessoas</label>
          <select
            id="people"
            value={people}
            onChange={(e) => setPeople(parseInt(e.target.value))}
            className="people-select"
          >
            {[1, 2, 3, 4, 5, 6, 7, 8].map(num => (
              <option key={num} value={num}>
                {num} {num === 1 ? 'pessoa' : 'pessoas'}
              </option>
            ))}
          </select>
        </div>
      </div>

      <div className="availability-section">
        <h3>Datas Disponíveis</h3>
        <div className="date-cards">
          {dates.map((dateObj, index) => (
            <div 
              key={index}
              className={`date-card ${selectedDate === dateObj.date ? 'selected' : ''}`}
              onClick={() => setSelectedDate(dateObj.date)}
            >
              <h4>{dateObj.date}</h4>
              <div className="time-slots">
                {dateObj.times.map((time, timeIndex) => (
                  <span
                    key={timeIndex}
                    className={`time-slot ${selectedTime === time ? 'selected' : ''}`}
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
      </div>

      <button
        className="reserve-button"
        onClick={handleReservation}
        disabled={!name || !email || !phone || !selectedDate || !selectedTime}
      >
        Confirmar Reserva
      </button>
    </div>
  );
};

export default DateTimeSelection;