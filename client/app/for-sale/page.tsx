"use client";

import SaleCatalog from '@/components/SaleCatalog'
import { useAuthContext } from '@/context/AuthContext';
import { useRouter } from 'next/navigation';
import React, { useEffect, useState } from 'react'

const page = () => {

  const {isAuthenticated, setIsAuthenticated, internationalization } = useAuthContext();
  const [carCatalogTitle, setCarCatalogTitle] = useState("Catalog Cars for SALE");

  const router = useRouter();

  if (!isAuthenticated) {
    router.push("/login");
  }

  useEffect(() => {
    console.log("Internationalization switched! " + internationalization);

    if (internationalization === "Български") {
      setCarCatalogTitle("Каталог автомобили за ПРОДАЖБА");
    } else {
      setCarCatalogTitle("Catalog Cars for SALE");
    }
    
  }, [internationalization])

  return (
    <main className="overflow-hidden">
      <div className="">
        <h1 className="text-center text-4xl font-extrabold pt-32">{carCatalogTitle}</h1>
        <SaleCatalog />
      </div>
    </main>
  )
}

export default page