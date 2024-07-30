"use client";

import { useRouter } from "next/navigation";
import { useState } from "react";
import CustomButton from "./CustomButton";
import { loginUser } from "@/utils";

const LoginForm: React.FC = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [registrationError, setRegistrationError] = useState<string | null>(
      null
    );
  
    const router = useRouter();
  
    const handleLogin = async () => {
      try {
        const result = await loginUser(email, password);
  
        if (result == "logged") {
          router.push("/");
        }
      } catch (error) {
        setRegistrationError("Something went wrong. Please try again."); // Customize error message
      }
    };
  
    return (
      <form
        action={"/login"}
        className="registration-form"
      >
        <div>
          <label htmlFor="email" typeof="text" aria-placeholder="Email" />
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
  
        <div>
          <label htmlFor="password" typeof="text" aria-placeholder="Password" />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        {registrationError && <p>{registrationError}</p>}
        <div className="items-center px-4">
        <CustomButton 
                  title="Login"
                  btnType="button"
                  containerStyles='bg-blue-500 text-white rounded-md hover:bg-blue-600 transition duration-300 ease-in-out min-w-[400px]'
                  handleClick={handleLogin}
              />
        </div>
      </form>
    );
  };

export default LoginForm