import axios from 'axios';

// Create an Axios instance with a base URL
const apiClient = axios.create({
  baseURL: 'http://localhost:8090/api',
});

/**
 * Fetches a list of products from the backend.
 * 
 * @returns {Promise<Array>} A promise that resolves to an array of product objects.
 * @throws Will throw an error if the request fails.
 */
export const fetchProducts = async () => {
  try {
    const response = await apiClient.get('/products');
    return response.data;
  } catch (error) {
    console.error('Error fetching products:', error);
    throw new Error('Unable to fetch products at this time. Please try again later.');
  }
};

/**
 * Fetches a single product by its ID from the backend.
 * 
 * @param {string} id - The ID of the product to fetch.
 * @returns {Promise<Object>} A promise that resolves to the product object.
 * @throws Will throw an error if the request fails.
 */
export const fetchProductById = async (id) => {
  try {
    const response = await apiClient.get(`/products/${id}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching product data:', error);
    throw new Error(`Unable to fetch product with ID ${id}. Please try again later.`);
  }
};
