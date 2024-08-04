"use client";

import { ShopCatalogProps } from "@/types";
import CustomButton from "./CustomButton";
import { useAuthContext } from "@/context/AuthContext";
import { useEffect, useState } from "react";

const ShopCatalog = ({ pageNumber, isNext, setLimit }: ShopCatalogProps) => {

  const { internationalization } = useAuthContext();
  const [showMoreTitle, setShowMoreTitle] = useState("Show More");

  const handleNavigation = () => {
    // Calculate the new limit based on the page number and navigation type
    const newLimit = (pageNumber + 1) * 10;

    setLimit(newLimit);
  };

  useEffect(() => {
    console.log("Internationalization switched! " + internationalization);

    if (internationalization === "Български") {
      setShowMoreTitle("Покажи повече")
    }
    
  }, [internationalization])

  return (
    <div className="w-full flex-center gap-5 mt-10">
      {!isNext && (
        <CustomButton
          btnType="button"
          title={showMoreTitle}
          containerStyles="bg-primary-blue rounded-full text-white"
          handleClick={handleNavigation}
        />
      )}
    </div>
  );
};

export default ShopCatalog;
