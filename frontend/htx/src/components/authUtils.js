import * as jose from "jose";

export const getCurrentUser = (token) => {
    try {
        const decodedToken = jose.decodeJwt(token);
        if (decodedToken && decodedToken.role) {
            return decodedToken.role;
        } else {
            console.error('Unable to retrieve user ID from JWT token.');
            return null;
        }
    } catch (error) {
        console.error('Error decoding JWT token:', error);
        return null;
    }
};