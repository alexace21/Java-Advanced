"use client";

import OfferForm from '@/components/OfferForm'
import { useAuthContext } from '@/context/AuthContext';
import { getAuthToken, getAuthUser } from '@/utils';
import { useRouter } from 'next/navigation';
import React, { useEffect, useState } from 'react'

const page = () => {

  const { internationalization, role } = useAuthContext();
  const [createOfferPageTitle, setCreateOfferPageTitle] = useState("Create Offer");

  const router = useRouter();

  useEffect(() => {
    console.log("Internationalization switched! " + internationalization);

    if (internationalization === "Български") {
      setCreateOfferPageTitle("Създай Оферта");
    } else {
      setCreateOfferPageTitle("Create Offer");
    }
    
  }, [internationalization])
  
  if (!getAuthToken() || !getAuthUser() || !role ) {
    router.push("/login");
  } else {
    return (
      <main className="overflow-hidden">
        <div className="">
          <h1 className="text-center text-4xl font-extrabold pt-32">{createOfferPageTitle}</h1>
          <OfferForm />
        </div>
      </main>
    )
  }
  
}

export default page