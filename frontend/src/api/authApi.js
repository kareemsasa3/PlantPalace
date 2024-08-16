import axios from 'axios';

// Create an Axios instance with a base URL
const apiClient = axios.create({
  baseURL: 'http://localhost:8090/api',
});

apiClient.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('jwtToken');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export const signup = async (userData) => {
    try {
        const response = await apiClient.post('/users', userData);
        return response.data; // Adjust based on your API response
    } catch (error) {
        console.error('Signup error:', error);
        throw error; // Rethrow to handle it in the component
    }
};

export const login = async (credentials) => {
    try {
        const response = await apiClient.post('/auth/login', credentials);
        const { token, user } = response.data;
        localStorage.setItem('jwtToken', token);
        localStorage.setItem('user', JSON.stringify(user));
        return response.data;
    } catch (error) {
        console.error('login error: ', error);
        throw error;
    }
};

export const logout = () => {
    localStorage.removeItem('jwtToken');
    localStorage.removeItem('user');
};