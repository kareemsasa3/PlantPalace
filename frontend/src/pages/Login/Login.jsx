import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { Link, useNavigate } from 'react-router-dom';
import './Login.css';
import { login } from '../../api/authApi';

const Login = ({ redirectTo = '/account' }) => {  // Default parameter value
    const [credentials, setCredentials] = useState({ username: '', password: '' });
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            await login(credentials);
            console.log('Login successful');
            navigate(redirectTo);
        } catch (err) {
            setError('Login failed. Please check your credentials and try again.');
        }
    };

    return (
        <div className="login-container">
            <form className="login-form" onSubmit={handleLogin}>
                <h2>LOGIN</h2>
                <div className="form-group">
                    <label htmlFor="username">Username</label>
                    <input
                        type="text"
                        id="username"
                        value={credentials.username}
                        onChange={(e) => setCredentials({ ...credentials, username: e.target.value })}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password</label>
                    <input
                        type="password"
                        id="password"
                        value={credentials.password}
                        onChange={(e) => setCredentials({ ...credentials, password: e.target.value })}
                        required
                    />
                </div>
                <button type="submit" className="login-btn">Login</button>
                {error && <p className="error-message">{error}</p>}
                <div className="login-links">
                    <Link to="/forgot-password">Forgot Password?</Link>
                    <Link to="/signup">Create An Account</Link>
                </div>
            </form>
        </div>
    );
};

Login.propTypes = {
    redirectTo: PropTypes.string
};

export default Login;