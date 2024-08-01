"use client";

import LoginForm from '@/components/LoginForm'
import { useAuthContext } from '@/context/AuthContext';
import { useRouter } from 'next/navigation';
import React from 'react'

const page = () => {

  const {isAuthenticated, setIsAuthenticated} = useAuthContext();
  const router = useRouter();

  if (isAuthenticated) {
    router.push("/");
  }

  return (
    <main className="overflow-hidden">
      <div className="">
        <h1 className="text-center text-4xl font-extrabold pt-32">Login</h1>
        <LoginForm />
      </div>
    </main>
  )
}

export default page