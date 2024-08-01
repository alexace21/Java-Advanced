"use client"

import React, { useEffect, useState } from 'react'
import CarCard from './CarCard';
import ShopCatalog from './ShopCatalog';
import Image from "next/image";
import { fetchCarsForSale } from '@/utils';

const SaleCatalog = () => {
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

  useEffect(() => {
    getCars();

  }, [])

  return (
    <>
    {allCars.length > 0 ? (
            <section>
              <div className="home__cars-wrapper">
                {allCars?.map((car) => (
                  <CarCard car={car} />
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