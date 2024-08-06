"use client";

import SaleCatalog from '@/components/SaleCatalog'
import { useAuthContext } from '@/context/AuthContext';
import { getAuthToken, getAuthUser } from '@/utils';
import { useRouter } from 'next/navigation';
import React, { useEffect, useState } from 'react'

const page = () => {

  const { internationalization, role } = useAuthContext();
  const [carCatalogTitle, setCarCatalogTitle] = useState("Catalog Cars for SALE");

  const router = useRouter();

  useEffect(() => {
    console.log("Internationalization switched! " + internationalization);

    if (internationalization === "Български") {
      setCarCatalogTitle("Каталог автомобили за ПРОДАЖБА");
    } else {
      setCarCatalogTitle("Catalog Cars for SALE");
    }
    
  }, [internationalization])

  if (!getAuthToken() || !getAuthUser() || !role ) {
    router.push("/login");
  } else {
    return (
      <main className="overflow-hidden">
        <div className="">
          <h1 className="text-center text-4xl font-extrabold pt-32">{carCatalogTitle}</h1>
          <SaleCatalog />
        </div>
      </main>
    )
  }

  
}

export default page