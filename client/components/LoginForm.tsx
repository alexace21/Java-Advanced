"use client";

import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import CustomButton from "./CustomButton";
import { getAuthToken, loginUser } from "@/utils";
import Link from "next/link";
import { useAuthContext } from "@/context/AuthContext";

const LoginForm: React.FC = () => {
  const { isAuthenticated, setIsAuthenticated, internationalization } = useAuthContext();
  
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [loginError, setLoginError] = useState<string | null>(null);
  const [nonExistingAccountText, setNonExistingAccountText] = useState("Don't have an account?");
  const [loginTitle, setLoginTitle] = useState("Login")
  const [signInText, setSignInText] = useState("Sign Up");

  const [emailText, setEmailText] = useState("Email");
  const [passwordText, setPasswordText] = useState("Password");


  const router = useRouter();

  const handleLogin = async () => {
    try {
      const result = await loginUser(email, password);

      if (result === 200 && getAuthToken() != null) {
        window.localStorage.setItem("auth_user", email);
        setIsAuthenticated(true);
        router.push("/");
      } else {
        setLoginError(result);
      }
    } catch (error) {
      setLoginError("Something went wrong. Please try again."); // Customize error message
    }
  };

  useEffect(() => {
    console.log("Internationalization switched! " + internationalization);

    if (internationalization === "Български") {
        setLoginTitle("Влез");
        setNonExistingAccountText("Нямате регистрация?");
        setSignInText("Регистрация");

        setEmailText("Електронна Поща");
        setPasswordText("Парола");
    } else {
        setLoginTitle("Login");
        setNonExistingAccountText("Don't have an account?");
        setSignInText("Sign Up");

        setEmailText("Email");
        setPasswordText("Password");
    }
    
  }, [internationalization])

  return (
    <form action={"/login"} className="registration-form">
      <div>
        <label htmlFor="email" typeof="text" aria-placeholder="Email" />
        <input
          type="email"
          placeholder={emailText}
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="password" typeof="text" aria-placeholder="Password" />
        <input
          type="password"
          placeholder={passwordText}
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>

      <div>
        <span className="font-bold ml-5 mr-5">{nonExistingAccountText}</span>
        <Link href="/register">
          <button
            className="text-blue-700 font-semibold"
            type="button"
          >{signInText}</button>
        </Link>
      </div>

      {loginError && <p>{loginError}</p>}
      <div className="items-center px-4">
        <CustomButton
          title={loginTitle}
          btnType="button"
          containerStyles="bg-blue-500 text-white rounded-md hover:bg-blue-600 transition duration-300 ease-in-out min-w-[400px]"
          handleClick={handleLogin}
        />
      </div>
    </form>
  );
};

export default LoginForm;
