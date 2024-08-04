"use client";

import React, { useEffect, useState } from 'react'
import CustomButton from './CustomButton';
import { createCarOffer, getAuthToken } from '@/utils';
import { useRouter } from 'next/navigation';
import { useAuthContext } from '@/context/AuthContext';

const OfferForm = () => {
    const { internationalization } = useAuthContext();
    const [cityConsumptionText, setCityConsumptionText] = useState("City Consumption");
    const [combinedConsumptionText, setCombinedConsumptionText] = useState("Combined Consumption");
    const [cylindersText, setCylindersText] = useState("Cylinders");
    const [displacementText, setDisplacementText] = useState("Displacement");
    const [driveText, setDriveText] = useState("Drive");
    const [fuelTypeText, setFuelTypeText] = useState("Fuel Type");
    const [highwayConsumptionText, setHighwayConsumptionText] = useState("Highway Consumption");
    const [carMakeText, setCarMakeText] = useState("Car Make");
    const [carModelText, setCarModelText] = useState("Car Model");
    const [carTransmissionText, setCarTransmissionText] = useState("Car Transmission");
    const [carYearText, setCarYearText] = useState("Car Year");
    const [carPriceText, setCarPriceText] = useState("Car Price");
    const [createOfferText, setCreateOfferText] = useState("Create Offer")


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
    const [price, setPrice] = useState("");

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
            !year ||
            !price 
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
            year,
            price
        );
    
          if (result && getAuthToken() != null) {
            router.push("/for-sale");
          } else {
            setOfferError(result);
          }
        } catch (error) {
          setOfferError("Something went wrong. Please try again."); // Customize error message
        }
      };

      useEffect(() => {
        console.log("Internationalization switched! " + internationalization);
    
        if (internationalization === "Български") {
          setCityConsumptionText("Градска консумация");
          setCombinedConsumptionText("Обединена консумация");
          setCylindersText("Цилиндри");
          setDisplacementText("Обем на двигателя");
          setDriveText("Вид на предавките");
          setFuelTypeText("Вид гориво");
          setHighwayConsumptionText("Консумация извънградско");
          setCarMakeText("Марка на автомобила");
          setCarModelText("Модел на автомобила");
          setCarTransmissionText("Трансмисия на автомобила");
          setCarYearText("Година на автомобила");
          setCarPriceText("Цена на автомобила");
          setCreateOfferText("Създай оферта");
        } else {
          setCityConsumptionText("City Consumption");
          setCombinedConsumptionText("Combined Consumption");
          setCylindersText("Cylinders");
          setDisplacementText("Displacement");
          setDriveText("Drive");
          setFuelTypeText("Fuel Type");
          setHighwayConsumptionText("Highway Consumption");
          setCarMakeText("Car Make");
          setCarModelText("Car Model");
          setCarTransmissionText("Car Transmission");
          setCarYearText("Car Year");
          setCarPriceText("Car Price");
          setCreateOfferText("Create Offer");
        }
        
      }, [internationalization])

  return (
    <form className="registration-form">
      <div>
        <label htmlFor="city_mpg" typeof="text" aria-placeholder="City Consumption" />
        <input
          type="city_mpg"
          placeholder={cityConsumptionText}
          value={city_mpg}
          onChange={(e) => setCity_mpg(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="combination_mpg" typeof="text" aria-placeholder="Combined Consumption" />
        <input
          type="combination_mpg"
          placeholder={combinedConsumptionText}
          value={combination_mpg}
          onChange={(e) => setCombination_mpg(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="cylinders" typeof="text" aria-placeholder="Cylinders" />
        <input
          type="cylinders"
          placeholder={cylindersText}
          value={cylinders}
          onChange={(e) => setCylinders(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="displacement" typeof="text" aria-placeholder="Displacement" />
        <input
          type="displacement"
          placeholder={displacementText}
          value={displacement}
          onChange={(e) => setDisplacement(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="drive" typeof="text" aria-placeholder="Drive" />
        <input
          type="drive"
          placeholder={driveText}
          value={drive}
          onChange={(e) => setDrive(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="fuel_type" typeof="text" aria-placeholder="Fuel Type" />
        <input
          type="fuel_type"
          placeholder={fuelTypeText}
          value={fuel_type}
          onChange={(e) => setFuel_type(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="highway_mpg" typeof="text" aria-placeholder="Highway Consumption" />
        <input
          type="highway_mpg"
          placeholder={highwayConsumptionText}
          value={highway_mpg}
          onChange={(e) => setHighway_mpg(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="make" typeof="text" aria-placeholder="Car Make" />
        <input
          type="make"
          placeholder={carMakeText}
          value={make}
          onChange={(e) => setMake(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="model" typeof="text" aria-placeholder="Car Model" />
        <input
          type="model"
          placeholder={carModelText}
          value={model}
          onChange={(e) => setModel(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="transmission" typeof="text" aria-placeholder="Car Transmission" />
        <input
          type="transmission"
          placeholder={carTransmissionText}
          value={transmission}
          onChange={(e) => setTransmission(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="year" typeof="text" aria-placeholder="Car year" />
        <input
          type="year"
          placeholder={carYearText}
          value={year}
          onChange={(e) => setYear(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="price" typeof="text" aria-placeholder="Car price" />
        <input
          type="price"
          placeholder={carPriceText}
          value={price}
          onChange={(e) => setPrice(e.target.value)}
        />
      </div>


      {offerError && <p>{offerError}</p>}
      <div className="items-center px-4">
        <CustomButton
          title={createOfferText}
          btnType="button"
          containerStyles="bg-blue-500 text-white rounded-md hover:bg-blue-600 transition duration-300 ease-in-out min-w-[400px]"
          handleClick={handleCreate}
        />
      </div>
    </form>
  )
}

export default OfferForm