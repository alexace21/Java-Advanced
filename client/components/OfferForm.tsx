"use client";

import React, { useState } from 'react'
import CustomButton from './CustomButton';
import { createCarOffer, getAuthToken } from '@/utils';
import { useRouter } from 'next/navigation';

const OfferForm = () => {

    const [city_mpg, setCity_mpg] = useState("");
    const [combination_mpg, setCombination_mpg] = useState("");
    const [cylinders, setCylinders] = useState("");
    const [displacement, setDisplacement] = useState("");
    const [drive, setDrive] = useState("");
    const [fuel_type, setFuel_type] = useState("");
    const [highway_mpg, setHighway_mpg] = useState("");
    const [make, setMake] = useState("");
    const [model, setModel] = useState("");
    const [transmission, setTransmission] = useState("");
    const [year, setYear] = useState("");

    const [offerError, setOfferError] = useState<string | null>(null);

    const router = useRouter();

    const handleCreate = async () => {

        if (
            !city_mpg || 
            !combination_mpg ||
            !cylinders ||
            !displacement ||
            !drive ||
            !fuel_type ||
            !highway_mpg ||
            !make ||
            !model ||
            !transmission ||
            !year
        ) {
            setOfferError("Please fill out all fields!")
            return;
        }

        try {
          const result = await createCarOffer(
            city_mpg, 
            combination_mpg,
            cylinders,
            displacement,
            drive,
            fuel_type,
            highway_mpg,
            make,
            model,
            transmission,
            year
        );
    
          if (result === 200 && getAuthToken() != null) {
            router.push("/");
          } else {
            setOfferError(result);
          }
        } catch (error) {
          setOfferError("Something went wrong. Please try again."); // Customize error message
        }
      };


  return (
    <form className="registration-form">
      <div>
        <label htmlFor="city_mpg" typeof="text" aria-placeholder="City Consumption" />
        <input
          type="city_mpg"
          placeholder="City Consumption"
          value={city_mpg}
          onChange={(e) => setCity_mpg(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="combination_mpg" typeof="text" aria-placeholder="Combined Consumption" />
        <input
          type="combination_mpg"
          placeholder="Combined Consumption"
          value={combination_mpg}
          onChange={(e) => setCombination_mpg(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="cylinders" typeof="text" aria-placeholder="Cylinders" />
        <input
          type="cylinders"
          placeholder="Cylinders"
          value={cylinders}
          onChange={(e) => setCylinders(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="displacement" typeof="text" aria-placeholder="Displacement" />
        <input
          type="displacement"
          placeholder="Displacement"
          value={displacement}
          onChange={(e) => setDisplacement(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="drive" typeof="text" aria-placeholder="Drive" />
        <input
          type="drive"
          placeholder="Drive"
          value={drive}
          onChange={(e) => setDrive(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="fuel_type" typeof="text" aria-placeholder="Fuel Type" />
        <input
          type="fuel_type"
          placeholder="Fuel Type"
          value={fuel_type}
          onChange={(e) => setFuel_type(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="highway_mpg" typeof="text" aria-placeholder="Highway Consumption" />
        <input
          type="highway_mpg"
          placeholder="Highway Consumption"
          value={highway_mpg}
          onChange={(e) => setHighway_mpg(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="make" typeof="text" aria-placeholder="Car Make" />
        <input
          type="make"
          placeholder="Car Make"
          value={make}
          onChange={(e) => setMake(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="model" typeof="text" aria-placeholder="Car Model" />
        <input
          type="model"
          placeholder="Car Model"
          value={model}
          onChange={(e) => setModel(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="transmission" typeof="text" aria-placeholder="Car Transmission" />
        <input
          type="transmission"
          placeholder="Car Transmission"
          value={transmission}
          onChange={(e) => setTransmission(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="year" typeof="text" aria-placeholder="Car year" />
        <input
          type="year"
          placeholder="Car Year"
          value={year}
          onChange={(e) => setYear(e.target.value)}
        />
      </div>


      {offerError && <p>{offerError}</p>}
      <div className="items-center px-4">
        <CustomButton
          title="Create Offer"
          btnType="button"
          containerStyles="bg-blue-500 text-white rounded-md hover:bg-blue-600 transition duration-300 ease-in-out min-w-[400px]"
          handleClick={handleCreate}
        />
      </div>
    </form>
  )
}

export default OfferForm