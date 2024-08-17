import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types'; // Import PropTypes
import { Button } from 'semantic-ui-react';
import './AgeVerification.css'; // Import your custom CSS for styling the modal

const AgeVerification = ({ onConfirm }) => {
  const [isVerified, setIsVerified] = useState(false);

  // Duration in milliseconds (1 hour)
  const verificationDuration = 60 * 60 * 1000; // 1 hour

  useEffect(() => {
    const storedExpiryTime = localStorage.getItem('verificationExpiryTime');
    const ageVerified = localStorage.getItem('ageVerified');
    const currentTime = new Date().getTime();

    if (storedExpiryTime && ageVerified === 'true' && currentTime < storedExpiryTime) {
      setIsVerified(true);
      onConfirm(); // Automatically confirm if already verified and not expired
    } else {
      // If not verified or expired, reset verification status
      localStorage.removeItem('ageVerified');
      localStorage.removeItem('verificationExpiryTime');
    }
  }, [onConfirm]);

  const handleConfirm = () => {
    setIsVerified(true);
    const currentTime = new Date().getTime();
    localStorage.setItem('ageVerified', 'true');
    localStorage.setItem('verificationExpiryTime', currentTime + verificationDuration); // Set expiry time
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

// Define prop types
AgeVerification.propTypes = {
  onConfirm: PropTypes.func.isRequired, // onConfirm should be a function and is required
};

export default AgeVerification;
