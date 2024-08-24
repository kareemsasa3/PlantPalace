import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  wishlist: [],
  cart: [], // Array of objects: { productId, quantity }
  products: [], // Array to store product details
};

const shopSlice = createSlice({
  name: 'shop',
  initialState,
  reducers: {
    addToWishlist: (state, action) => {
      const productId = action.payload;
      if (!state.wishlist.includes(productId)) {
        state.wishlist.push(productId);
      }
    },
    removeFromWishlist: (state, action) => {
      const productId = action.payload;
      state.wishlist = state.wishlist.filter(id => id !== productId);
    },
    resetWishlist: (state) => {
      state.wishlist = [];
    },
    addToCart: (state, action) => {
      const { productId, quantity } = action.payload;
      const existingCartItem = state.cart.find(item => item.productId === productId);

      if (existingCartItem) {
        existingCartItem.quantity += quantity; // Update quantity if item already exists
      } else {
        state.cart.push({ productId, quantity });
      }
    },
    removeFromCart: (state, action) => {
      const { productId } = action.payload;
      const existingCartItem = state.cart.find(item => item.productId === productId);

      if (existingCartItem) {
        if (existingCartItem.quantity > 1) {
          existingCartItem.quantity -= 1; // Reduce quantity if more than one
        } else {
          state.cart = state.cart.filter(item => item.productId !== productId); // Remove item if quantity is zero
        }
        state.products = state.products.filter(product => product.id !== productId); // Remove from products list
      }
    },
    updateQuantity: (state, action) => {
      const { productId, quantity } = action.payload;
      const cartItem = state.cart.find(item => item.productId === productId);

      if (cartItem) {
        if (quantity <= 0) {
          state.cart = state.cart.filter(item => item.productId !== productId);
        } else {
          cartItem.quantity = quantity;
        }
      }
      
      // Update products list if necessary
      const productIndex = state.products.findIndex(product => product.id === productId);
      if (productIndex > -1) {
        if (quantity <= 0) {
          state.products.splice(productIndex, 1);
        } else {
          state.products[productIndex].quantity = quantity;
        }
      }
    },
    addToProducts: (state, action) => {
      const product = action.payload;
      const existingProductIndex = state.products.findIndex(p => p.id === product.id);
      if (existingProductIndex > -1) {
        state.products[existingProductIndex] = product;
      } else {
        state.products.push(product);
      }
    },
    resetCart: (state) => {
      state.cart = [];
      state.products = [];
    },
  },
});

export const {
  addToWishlist,
  removeFromWishlist,
  resetWishlist,
  addToCart,
  removeFromCart,
  updateQuantity,
  addToProducts,
  resetCart,
} = shopSlice.actions;

export default shopSlice.reducer;
