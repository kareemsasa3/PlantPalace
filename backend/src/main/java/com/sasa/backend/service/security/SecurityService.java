package com.sasa.backend.service.security;

import com.sasa.backend.entity.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public User getAuthenticatedUser() {
        // Check if there's a currently logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            return (User) authentication.getPrincipal(); // Assuming UserDetailsService returns User object
        }
        return null; // No user is logged in (guest checkout)
    }
}
