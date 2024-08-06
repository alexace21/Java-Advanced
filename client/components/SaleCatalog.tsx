"use client"

import React, { useEffect, useState } from 'react'
import CarCard from './CarCard';
import ShopCatalog from './ShopCatalog';
import Image from "next/image";
import { deleteCarForSale, fetchCarsForSale } from '@/utils';
import { CarCardProps } from '@/types';
import { useRouter } from 'next/navigation';
import { useAuthContext } from '@/context/AuthContext';

const SaleCatalog = () => {
    const {setIsAuthenticated} = useAuthContext();
    const router = useRouter();
    const [allCars, setAllCars] = useState([]);
    const [loading, setLoading] = useState(false);
  
    // pagination states
    const [limit, setLimit] = useState(10);
    
  const getCars = async () => {
    setLoading(true);

    try {
      const result = await fetchCarsForSale();
  
      setAllCars(result);
    } catch (error) {
      console.error(error);      
    } finally {
      setLoading(false);
    }
  };

  const handleDeleteCar = async (id: number) => {
    console.log("attempted delete of Car Sale Offer! - " + id);
    const result = await deleteCarForSale(id);

    if (result === 403) {
      setIsAuthenticated(false);
      window.localStorage.removeItem("auth_token");
      window.localStorage.removeItem("auth_user");
      alert("You don't have access to this resource!");
      router.push("/");
    }

    if (result == 200) {
      const filteredCars =  allCars.filter((car: CarCardProps) => car.id != id)
      setAllCars(filteredCars);
    } else {
      alert("Something went wrong with delete of car operation! Please try again!");
    }
  };

  useEffect(() => {
    getCars();

  }, [])

  useEffect(() => {

  }, [allCars])

  return (
    <>
    {allCars.length > 0 ? (
            <section>
              <div className="home__cars-wrapper">
                {allCars?.map((car) => (
                  <CarCard car={car} handleDeleteCar={handleDeleteCar}/>
                ))}
              </div>

              {loading && (
                <div className="mt-16 w-full flex-center">
                  <Image 
                    src="/loader.svg"
                    alt="loader"
                    width={50}
                    height={50}
                    className="object-contain"
                  />
                </div>
              )}
              <ShopCatalog
                pageNumber={limit / 10}
                isNext={limit > allCars.length}
                setLimit={setLimit}
              />
            </section>
          ) : (
            <div className="home__error-container">
              <h2 className="text-black text-xl">Oops, no results</h2>
              {/* <p>{allCars?.message}</p> */}
            </div>
          )}
    </>
    
  )
}

export default SaleCatalog