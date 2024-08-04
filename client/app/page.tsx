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
import { usePathname } from 'next/navigation';
import { useAuthContext } from "@/context/AuthContext";

export default function Home() {

  const { internationalization } = useAuthContext();

  const [carCatalogTitle, setCarCatalogTitle] = useState("Car Catalogue");
  const [subCatalogTitle, setSubCatalogTitle] = useState("Discover the car of your dreams!")
  const [fuelTitle, setFuelTitle] = useState("Fuel");
  const [yearTitle, setYearTitle] = useState("Year");

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


  useEffect(() => {
    console.log("Internationalization switched! " + internationalization);

    if (internationalization === "Български") {
        setCarCatalogTitle("Каталог на автомобили");
        setSubCatalogTitle("Открийте автомобила на мечтите си!");
        setFuelTitle("Гориво");
        setYearTitle("Година");
    }
    
  }, [internationalization])

  return (
    <main className="overflow-hidden">
      {showHeroSection && 
      <>
        <Hero />

        <div className="mt-12 padding-x padding-y max-width" id="discover">
          <div className="home__text-container">
            <h1 className="text-4xl font-extrabold">{carCatalogTitle}</h1>

            <p>{subCatalogTitle}</p>
          </div>

          <div className="home__filters">
            <SearchBar setManufacturer={setManufacturer} setModel={setModel}/>

            <div className="home__filter-container">
              <CustomFilter title={fuelTitle} options={fuels} setFilter={setFuel}/>
              <CustomFilter title={yearTitle} options={yearsOfProduction} setFilter={setYear}/>
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
