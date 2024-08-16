import React, { useState, useEffect } from 'react';
import { Button } from 'semantic-ui-react';
import './AgeVerification.css'; // Import your custom CSS for styling the modal

const AgeVerification = ({ onConfirm }) => {
  const [isVerified, setIsVerified] = useState(false);

  useEffect(() => {
    const ageVerified = localStorage.getItem('ageVerified');
    if (ageVerified === 'true') {
      setIsVerified(true);
      onConfirm(); // Automatically confirm if already verified
    }
  }, [onConfirm]);

  const handleConfirm = () => {
    setIsVerified(true);
    localStorage.setItem('ageVerified', 'true'); // Store the verification status in local storage
    onConfirm();
  };

  const handleDeny = () => {
    window.location.href = 'https://example.com/underage'; // Redirect to an underage page or show a message
  };

  if (isVerified) {
    return null; // If verified, do not render the modal
  }

  return (
    <div className="age-verification-modal">
        <h1 className='app-title'>Plant Palace</h1>
        <div className="modal-content">
            <h2>Are you over 21?</h2>
            <div className="modal-buttons">
            <Button onClick={handleConfirm}>Yes</Button>
            <Button onClick={handleDeny}>No</Button>
            </div>
        </div>
    </div>
  );
};

export default AgeVerification;
