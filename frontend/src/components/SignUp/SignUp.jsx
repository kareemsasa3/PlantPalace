import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button } from "semantic-ui-react";

import './SignUp.css';
import { signup } from "../../api/authApi";

const SignUp = () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    const [setError] = useState(null);
    const [setSuccess] = useState(null);

    const navigate = useNavigate();

    // Mark the function as async
    const handleSubmit = async (e) => {
        e.preventDefault();

        if (password !== confirmPassword) {
            alert("Passwords do not match!");
            return;
        }
        
        const userData = {
          username,
          email,
          password
        };

        try {
          await signup(userData);
          setSuccess('Sign up successful!');
          setError(null);
          navigate('/shop');
        } catch (error) {
          setError('Sign up failed. Please try again.');
          alert(error);
          setSuccess(null);
        }
    };

    return (
        <div className="signup-container">
          <form className="signup-form" onSubmit={handleSubmit}>
            <h2>SIGN UP</h2>
            <div className="form-group">
              <label htmlFor="username">Username</label>
              <input
                type="text"
                id="username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="email">Email</label>
              <input
                type="email"
                id="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="password">Password</label>
              <input
                type="password"
                id="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="confirm-password">Confirm Password</label>
              <input
                type="password"
                id="confirm-password"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
                required
              />
            </div>
            <Button type="submit" className="signup-btn">Sign Up</Button>
            <div className="signup-links">
                <Link to="/login">Already have an account? Login</Link>
            </div>
          </form>
        </div>
    );
};

export default SignUp;
