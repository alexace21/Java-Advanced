"use client";

import React, { useEffect, useState } from 'react'
import CustomButton from './CustomButton';
import { createCarOffer, getAuthToken } from '@/utils';
import { useRouter } from 'next/navigation';
import { useAuthContext } from '@/context/AuthContext';

const OfferForm = () => {
    const { internationalization, setIsAuthenticated } = useAuthContext();
    const [cityConsumptionText, setCityConsumptionText] = useState("City Consumption: 9");
    const [combinedConsumptionText, setCombinedConsumptionText] = useState("Combined Consumption: 7");
    const [cylindersText, setCylindersText] = useState("Cylinders: 5");
    const [displacementText, setDisplacementText] = useState("Displacement: 4.5");
    const [driveText, setDriveText] = useState("Drive: FWD,RWD,AWD,4WD");
    const [fuelTypeText, setFuelTypeText] = useState("Fuel Type: Gas");
    const [highwayConsumptionText, setHighwayConsumptionText] = useState("Highway Consumption: 6");
    const [carMakeText, setCarMakeText] = useState("Car Make: Toyota");
    const [carModelText, setCarModelText] = useState("Car Model: Corolla");
    const [carTransmissionText, setCarTransmissionText] = useState("Car Transmission: Manual");
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
          if (internationalization === "Български") {
            setOfferError("Моля попълнете всички празни полета!");
          } else {
            setOfferError("Please fill out all fields!");
          }
            return;
        }

        if (isNaN(Number(city_mpg)) || (Number(city_mpg) <= 0)) {
          if (internationalization === "Български") {
            setOfferError("Невалидна Градска консумация!");
          } else {
            setOfferError("Invalid City Consumption!");
          }
          return;
        }

        if (isNaN(Number(combination_mpg)) || (Number(combination_mpg) <= 0)) {
          if (internationalization === "Български") {
            setOfferError("Невалидна Обединена консумация!");
          } else {
            setOfferError("Invalid Combined Consumption!");
          }
          return;
        }

        if (isNaN(Number(cylinders)) || (Number(cylinders) < 2)) {
          if (internationalization === "Български") {
            setOfferError("Невалидни Цилиндри!");
          } else {
            setOfferError("Invalid Cylinders!");
          }
          return;
        }

        if (isNaN(Number(displacement)) || (Number(displacement) < 1.0)) {
          if (internationalization === "Български") {
            setOfferError("Невалиден Обем на двигателя!");
          } else {
            setOfferError("Invalid Displacement!");
          }
          return;
        }

        if (drive.length < 3) {
          if (internationalization === "Български") {
            setOfferError("Невалиден Вид на предавките!");
          } else {
            setOfferError("Invalid Drive!");
          }
          return;
        }

        if (fuel_type.length < 3) {
          if (internationalization === "Български") {
            setOfferError("Невалидено Вид Гориво!");
          } else {
            setOfferError("Invalid Fuel Type!");
          }
          return;
        }

        if (isNaN(Number(highway_mpg)) || (Number(highway_mpg) <= 0)) {
          if (internationalization === "Български") {
            setOfferError("Невалидна Консумация извънградско!");
          } else {
            setOfferError("Invalid Highway Consumption!");
          }
          return;
        }

        if (transmission.length < 5) {
          if (internationalization === "Български") {
            setOfferError("Невалидна Трансмисия на автомобила!");
          } else {
            setOfferError("Invalid Transmission Type!");
          }
          return;
        }

        if (isNaN(Number(year)) || (Number(year) <= 0) || (Number(year) < 1960) || (Number(year) > 2024)) {
          if (internationalization === "Български") {
            setOfferError("Невалидна Година на производство!");
          } else {
            setOfferError("Invalid Car Year!");
          }
          return;
        }

        if (isNaN(Number(price)) || (Number(price) <= 0)) {
          if (internationalization === "Български") {
            setOfferError("Невалидна Цена на автомобила!");
          } else {
            setOfferError("Invalid Car Price!");
          }
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

        if (result === "Unauthorized path") {
          setIsAuthenticated(false);
          window.localStorage.removeItem("auth_token");
          window.localStorage.removeItem("auth_user");
          alert("Login has expired!");
          router.push("/login");
        }
    
          if (result instanceof Object && getAuthToken() != null) {
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
          setCityConsumptionText("Градска консумация: 9");
          setCombinedConsumptionText("Обединена консумация: 7");
          setCylindersText("Цилиндри: 2");
          setDisplacementText("Обем на двигателя: 1.0");
          setDriveText("Вид на предавките: Предно");
          setFuelTypeText("Вид гориво: Дизел");
          setHighwayConsumptionText("Консумация извънградско: 6");
          setCarMakeText("Марка на автомобила: Toyota");
          setCarModelText("Модел на автомобила: Corolla");
          setCarTransmissionText("Трансмисия на автомобила: Автоматик");
          setCarYearText("Година на автомобила");
          setCarPriceText("Цена на автомобила");
          setCreateOfferText("Създай оферта");
        } else {
          setCityConsumptionText("City Consumption: 9");
          setCombinedConsumptionText("Combined Consumption: 7");
          setCylindersText("Cylinders: 5");
          setDisplacementText("Displacement: 4.5");
          setDriveText("Drive: FWD,RWD,AWD,4WD");
          setFuelTypeText("Fuel Type: Gas");
          setHighwayConsumptionText("Highway Consumption: 6");
          setCarMakeText("Car Make: Toyota");
          setCarModelText("Car Model: Corolla");
          setCarTransmissionText("Car Transmission: Manual");
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