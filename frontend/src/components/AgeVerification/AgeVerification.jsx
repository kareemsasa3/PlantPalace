import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import './AgeVerification.css';

const AgeVerification = ({ onConfirm }) => {
  const [isVerified, setIsVerified] = useState(false);
  const verificationDuration = 60 * 60 * 1000; // 1 hour

  useEffect(() => {
    const storedExpiryTime = localStorage.getItem('verificationExpiryTime');
    const ageVerified = JSON.parse(localStorage.getItem('ageVerified'));
    const currentTime = Date.now();

    if (storedExpiryTime && ageVerified === 'true' && currentTime < storedExpiryTime) {
      setIsVerified(true);
      onConfirm();
    } else {
      localStorage.removeItem('ageVerified');
      localStorage.removeItem('verificationExpiryTime');
    }
  }, [onConfirm]);

  const handleConfirm = () => {
    const currentTime = Date.now();
    setIsVerified(true);
    localStorage.setItem('ageVerified', JSON.stringify('true'));
    localStorage.setItem('verificationExpiryTime', currentTime + verificationDuration);
    onConfirm();
  };

  const handleDeny = () => {
    console.log('User not old enough to enter');
  };

  if (isVerified) {
    return null;
  }

  return (
    <div className="age-verification-modal">
      <h1 className='app-title'>Plant Palace</h1>
      <div className="modal-content">
        <h2>Are you over 21?</h2>
        <div className="modal-buttons">
          <button className="confirm-button" onClick={handleConfirm}>Yes</button>
          <button className="deny-button" onClick={handleDeny}>No</button>
        </div>
      </div>
    </div>
  );
};

AgeVerification.propTypes = {
  onConfirm: PropTypes.func.isRequired,
};

export default AgeVerification;