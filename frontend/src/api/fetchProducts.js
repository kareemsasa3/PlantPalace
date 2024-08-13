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

/**
 * Searches for products based on the given parameters.
 * 
 * @param {string} [name] - The name to search for.
 * @param {string} [category] - The category to filter by.
 * @param {string} [type] - The type to filter by.
 * @param {number} [minPrice] - The minimum price to filter by.
 * @param {number} [maxPrice] - The maximum price to filter by.
 * @returns {Promise<Array>} A promise that resolves to an array of product objects matching the search criteria.
 * @throws Will throw an error if the request fails.
 */
export const searchProducts = async (name, category, type, minPrice, maxPrice) => {
  try {
    // Filter out undefined parameters
    const params = {};
    if (name !== undefined) params.name = name;
    if (category !== undefined) params.category = category;
    if (type !== undefined) params.type = type;
    if (minPrice !== undefined) params.minPrice = minPrice;
    if (maxPrice !== undefined) params.maxPrice = maxPrice;

    const response = await apiClient.get('/products/search', { params });
    return response.data;
  } catch (error) {
    console.error('Error searching products:', error);
    throw new Error('Unable to search for products at this time. Please try again later.');
  }
};
