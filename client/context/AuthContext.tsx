"use client";

import { createContext, useContext, useState } from "react";

type AuthContextProviderProps = {
    children: React.ReactNode
}

type AuthContext = {
    isAuthenticated: true | false;
    setIsAuthenticated: React.Dispatch<React.SetStateAction<boolean>>;
    internationalization: string;
    setInternationalization: React.Dispatch<React.SetStateAction<string>>;
}

export const AuthContext = createContext<AuthContext | null>(null);

export default function AuthContextProvider({ children }: AuthContextProviderProps) {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [internationalization, setInternationalization] = useState("English");

    return (
        <AuthContext.Provider
            value={{
                isAuthenticated,
                setIsAuthenticated,
                internationalization,
                setInternationalization
            }}
        >
            {children}
        </AuthContext.Provider>
    );
}

export function useAuthContext() {
    const context = useContext(AuthContext);

    if (!context) {
        throw new Error(
            "useAuthContext must be used within a AuthContextProvider"
        );
    }

    return context;
}

