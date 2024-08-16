import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button } from "semantic-ui-react";
import './Login.css';
import { login } from "../../api/authApi";

const Login = () => {
    const [credentials, setCredentials] = useState({ username: '', password: '' });
    const [error, setError] = useState(null);
    const navigate = useNavigate(); // React Router hook for navigation

    const handleLogin = async (e) => {
        e.preventDefault(); // Prevents the default form submission behavior
        try {
            await login(credentials); // Assume login function handles storage
            console.log('Login successful');
            navigate('/account'); // Redirect to the /shop route
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
                <Button type="submit" className="login-btn">Login</Button>
                {error && <p className="error-message">{error}</p>}
                <div className="login-links">
                    <Link to="/forgot-password">Forgot Password?</Link>
                    <Link to="/signup">Create An Account</Link>
                </div>
            </form>
        </div>
    );
};

export default Login;
