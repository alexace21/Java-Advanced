"use client";

import OfferForm from '@/components/OfferForm'
import { useAuthContext } from '@/context/AuthContext';
import { useRouter } from 'next/navigation';
import React, { useEffect, useState } from 'react'

const page = () => {

  const { isAuthenticated, setIsAuthenticated, internationalization } = useAuthContext();
  const [createOfferPageTitle, setCreateOfferPageTitle] = useState("Create Offer");

  const router = useRouter();

  if (!isAuthenticated) {
    router.push("/login");
  }

  useEffect(() => {
    console.log("Internationalization switched! " + internationalization);

    if (internationalization === "Български") {
      setCreateOfferPageTitle("Създай Оферта");
    } else {
      setCreateOfferPageTitle("Create Offer");
    }
    
  }, [internationalization])

  return (
    <main className="overflow-hidden">
      <div className="">
        <h1 className="text-center text-4xl font-extrabold pt-32">{createOfferPageTitle}</h1>
        <OfferForm />
      </div>
    </main>
  )
}

export default page