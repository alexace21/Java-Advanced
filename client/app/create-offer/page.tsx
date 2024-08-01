"use client";

import OfferForm from '@/components/OfferForm'
import { useAuthContext } from '@/context/AuthContext';
import { useRouter } from 'next/navigation';
import React from 'react'

const page = () => {

  const {isAuthenticated, setIsAuthenticated} = useAuthContext();
  const router = useRouter();

  if (!isAuthenticated) {
    router.push("/login");
  }

  return (
    <main className="overflow-hidden">
      <div className="">
        <h1 className="text-center text-4xl font-extrabold pt-32">Create Offer</h1>
        <OfferForm />
      </div>
    </main>
  )
}

export default page