import React, { useState } from "react";

import { Link } from "react-router-dom";

import './Login.css';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log('Email: ', email);
        console.log('Password: ', password);
    }

    return (
        <div className="login-container">
          <form className="login-form" onSubmit={handleSubmit}>
            <h2>LOGIN</h2>
            <div className="form-group">
              <label htmlFor="email">EMAIL</label>
              <input
                type="text"
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
            <button type="submit">Login</button>
            <div className="login-links">
                <Link to="/forgot-password">Forgot Password?</Link>
                <Link to="/signup">Create An Account</Link>
            </div>
          </form>
        </div>
    );
};

export default Login;