"use client";

import LoginForm from '@/components/LoginForm'
import { useAuthContext } from '@/context/AuthContext';
import { useRouter } from 'next/navigation';
import React, { useEffect, useState } from 'react'

const page = () => {

  const { isAuthenticated, setIsAuthenticated, internationalization} = useAuthContext();
  const [loginTitle, setLoginTitle] = useState("Login");

  const router = useRouter();

  useEffect(() => {
    console.log("Internationalization switched! " + internationalization);

    if (internationalization === "Български") {
        setLoginTitle("Вход")
    } else {
        setLoginTitle("Login");
    }
    
  }, [internationalization])

  if (isAuthenticated) {
    router.push("/");
  } else {
    return (
      <main className="overflow-hidden">
        <div className="">
          <h1 className="text-center text-4xl font-extrabold pt-32">{loginTitle}</h1>
          <LoginForm />
        </div>
      </main>
    )
  }
  
}

export default page