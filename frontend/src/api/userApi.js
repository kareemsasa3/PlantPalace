// src/api/userApi.js

import axios from 'axios';

// Create an axios instance with default configuration
const apiClient = axios.create({
    baseURL: 'http://localhost:8090/api', // Base URL for API requests
    headers: {
        'Content-Type': 'application/json', // Default headers for JSON data
    },
});

// Function to update user details
export const updateUser = async (userId, userDTO) => {
    try {
        const response = await apiClient.put(`/users/${userId}`, userDTO, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('jwtToken')}`, // Include JWT token for authorization
            },
        });
        return response.data; // Return the updated user data from the server
    } catch (error) {
        console.error('Failed to update user:', error);
        throw error; // Re-throw the error to be handled by the calling function
    }
};

// You can add more functions here for other user-related API calls (e.g., getUser, deleteUser, etc.)
