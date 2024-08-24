import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8090/api',
});

/**
 * Fetches place suggestions based on user input from the backend.
 * 
 * @param {string} input - The user input for which to fetch place suggestions.
 * @returns {Promise<Object>} A promise that resolves to an object containing place suggestions.
 * @throws Will throw an error if the request fails.
 */
export const fetchPlaceSuggestions = async (input) => {
  try {
    const response = await apiClient.get('/places', {
      params: {
        input: input,
      },
    });

    if (response.data.status === 'OK') {
      // Use the full description for suggestions
      const processedSuggestions = response.data.predictions.map((prediction) => ({
        value: prediction.description,
        label: prediction.description
      }));
      return { predictions: processedSuggestions };
    }

    // Handle cases where status is not OK
    console.warn('Unexpected response status:', response.data.status);
    return { predictions: [] };
  } catch (error) {
    console.error('Error fetching place suggestions:', error);
    throw new Error('Unable to fetch place suggestions at this time. Please try again later.');
  }
};
