import axios from 'axios';

// Create an Axios instance with a base URL
const apiClient = axios.create({
  baseURL: 'http://localhost:8090/api',
});

export const signup = async (userData) => {
    try {
        const response = await apiClient.post('/users', userData);
        return response.data; // Adjust based on your API response
    } catch (error) {
        console.error('Signup error:', error);
        throw error; // Rethrow to handle it in the component
    }
};