"use client";

import RegistrationForm from "@/components/RegistrationForm";
import { useAuthContext } from "@/context/AuthContext";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const RegisterPage = () => {

  const {isAuthenticated, setIsAuthenticated, internationalization} = useAuthContext();
  const [registerTitle, setRegisterTitle] = useState("Register");
  const router = useRouter();

  if (isAuthenticated) {
    router.push("/");
  }


  useEffect(() => {
    console.log("Internationalization switched! " + internationalization);

    if (internationalization === "Български") {
      setRegisterTitle("Регистрация")
    } else {
      setRegisterTitle("Register");
    }
    
  }, [internationalization])

  return (
    <main className="overflow-hidden">
      <div className="">
        <h1 className="text-center text-4xl font-extrabold pt-32">{registerTitle}</h1>
        <RegistrationForm />
      </div>
    </main>
  );
};

export default RegisterPage;
