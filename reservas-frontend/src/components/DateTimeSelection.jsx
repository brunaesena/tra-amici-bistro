import React, { useState } from 'react';
import './DateTimeSelection.css';

const DateTimeSelection = () => {
  const [selectedDate, setSelectedDate] = useState(null);
  const [selectedTime, setSelectedTime] = useState(null);
  const [isReserved, setIsReserved] = useState(false);

  const dates = [
    {
      date: '20 de Abril (Sábado)',
      times: ['12:00', '13:30', '15:00']
    },
    {
      date: '21 de Abril (Domingo)',
      times: ['11:00', '13:00', '14:30']
    },
    {
      date: '22 de Abril (Segunda)',
      times: ['18:00', '19:30', '21:00']
    }
  ];

  const handleDateSelect = (date) => {
    setSelectedDate(date);
    setSelectedTime(null); // Reset time when changing date
  };

  const handleTimeSelect = (time) => {
    setSelectedTime(time);
  };

  const handleReservation = () => {
    if (selectedDate && selectedTime) {
      console.log(`Reserva confirmada para ${selectedDate} às ${selectedTime}`);
      setIsReserved(true);
      
      // Aqui você pode adicionar a lógica para enviar para o backend
      // Exemplo: api.reserveTable(selectedDate, selectedTime);
    }
  };

  if (isReserved) {
    return (
      <div className="datetime-container">
        <div className="reservation-confirmed">
          <div className="confirmation-icon">✓</div>
          <h2>Reserva Confirmada no Tra Amici!</h2>
          
          <div className="reservation-details">
            <p><span>Data:</span> {selectedDate}</p>
            <p><span>Horário:</span> {selectedTime}</p>
          </div>
  
          <button 
            className="reserve-button"
            onClick={() => {
              setIsReserved(false);
              setSelectedDate(null);
              setSelectedTime(null);
            }}
          >
            Fazer Nova Reserva
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="datetime-container">
      <h2>Selecione uma data e horário</h2>
      <div className="cards-wrapper">
        {dates.map((item, index) => (
          <div 
            className={`datetime-card ${selectedDate === item.date ? 'selected' : ''}`} 
            key={index}
            onClick={() => handleDateSelect(item.date)}
          >
            <h3>{item.date}</h3>
            <div className="times">
              {item.times.map((time, i) => (
                <span 
                  className={`time-slot ${selectedTime === time && selectedDate === item.date ? 'selected' : ''}`}
                  key={i}
                  onClick={(e) => {
                    e.stopPropagation();
                    handleTimeSelect(time);
                  }}
                >
                  {time}
                </span>
              ))}
            </div>
          </div>
        ))}
      </div>
      
      {selectedDate && selectedTime && (
        <div className="reservation-summary">
          <p>Você selecionou: {selectedDate} às {selectedTime}</p>
          <button 
            className="reserve-button" 
            onClick={handleReservation}
          >
            Confirmar Reserva
          </button>
        </div>
      )}
    </div>
  );
};

export default DateTimeSelection;
