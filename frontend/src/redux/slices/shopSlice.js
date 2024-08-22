import { createSlice } from '@reduxjs/toolkit';

// Initial state of the slice
const initialState = {
    wishlist: [],
    cart: [], // Use an array to store product objects with IDs and quantities
};

const shopSlice = createSlice({
    name: 'shop',
    initialState,
    reducers: {
        addToWishlist: (state, action) => {
            const productId = action.payload; // Assume action payload contains the product ID
            if (!state.wishlist.includes(productId)) {
                state.wishlist.push(productId); // Add to wishlist if not already present
            }
        },
        removeFromWishlist: (state, action) => {
            const productId = action.payload;
            state.wishlist = state.wishlist.filter((id) => id !== productId); // Remove from wishlist
        },
        resetWishlist: (state) => {
            state.wishlist = [];
        },
        addToCart: (state, action) => {
            const { productId, quantity } = action.payload;
            const existingProduct = state.cart.find(item => item.productId === productId);
            
            if (existingProduct) {
                existingProduct.quantity += quantity; // Increase quantity if product already in cart
            } else {
                state.cart.push({ productId, quantity }); // Add new product with quantity
            }
        },
        removeFromCart: (state, action) => {
            const productId = action.payload.toString();
            state.cart = state.cart.filter(item => item.productId !== productId);   
        },        
        updateQuantity: (state, action) => {
            const { productId, quantity } = action.payload;
            const existingProduct = state.cart.find(item => item.productId === productId);
            
            if (existingProduct) {
                if (quantity <= 0) {
                    // If quantity is 0 or less, remove the product from the cart
                    state.cart = state.cart.filter(item => item.productId !== productId);
                } else {
                    existingProduct.quantity = quantity; // Update quantity
                }
            }
        },
        resetCart: (state) => {
            state.cart = [];
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
    resetCart,
} = shopSlice.actions;

export default shopSlice.reducer;
