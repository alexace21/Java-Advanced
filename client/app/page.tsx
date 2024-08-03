"use client";

import {
  CarCard,
  CustomFilter,
  Hero,
  SearchBar,
  ShopCatalog,
} from "@/components";
import { fuels, yearsOfProduction } from "@/constants";
import { fetchCars } from "@/utils";
import Image from "next/image";
import { useEffect, useState } from "react";
import { useRouter, usePathname } from 'next/navigation';

export default function Home() {
  const [allCars, setAllCars] = useState([]);
  const [loading, setLoading] = useState(false);
  
  // search states
  const [manufacturer, setManufacturer] = useState("");
  const [model, setModel] = useState("");

  // filter states
  const [fuel, setFuel] = useState("")
  const [year, setYear] = useState("");

  // pagination states
  const [limit, setLimit] = useState(10);

  const [pathname, setPathname] = useState(usePathname())

  const showHeroSection = pathname === "/";

  const getCars = async () => {
    setLoading(true);

    try {
      const result = await fetchCars({
        manufacturer: manufacturer || "",
        year: year || "2022",
        fuel: fuel || "",
        limit: limit || 10,
        model: model || "",
      });
  
      setAllCars(result);
    } catch (error) {
      console.error(error);      
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    getCars();
  }, [fuel, year, limit, manufacturer, model])

  useEffect(() => {
    console.log("Logged changed pathname: " + pathname);
  }, [pathname])

  return (
    <main className="overflow-hidden">
      {showHeroSection && 
      <>
        <Hero />

        <div className="mt-12 padding-x padding-y max-width" id="discover">
          <div className="home__text-container">
            <h1 className="text-4xl font-extrabold">Car Catalogue</h1>

            <p>Discover the car of your dreams!</p>
          </div>

          <div className="home__filters">
            <SearchBar setManufacturer={setManufacturer} setModel={setModel}/>

            <div className="home__filter-container">
              <CustomFilter title="fuel" options={fuels} setFilter={setFuel}/>
              <CustomFilter title="year" options={yearsOfProduction} setFilter={setYear}/>
            </div>
          </div>

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
              <p>{allCars?.message}</p>
            </div>
          )}
        </div>
      </>
      }
    </main>
  );
}
