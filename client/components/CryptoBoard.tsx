"use client";

import { useAuthContext } from "@/context/AuthContext";
import { fetchAllCryptoData } from "@/utils";
import React, { useEffect, useState } from "react";

const CryptoBoard = () => {
    const { internationalization } = useAuthContext();
    const [headlineText, setHeadlineText] = useState("Crypto TODAY provided by");
    const [priceText, setPriceText] = useState("price");
    const [noResultsText, setNoResultsText] = useState("Oops, no data yet!")

    const [allCrypto, setAllCrypto] = useState([]);


    useEffect(() => {
        const result = fetchAllCryptoData();

        if (result) {   
            result.then(data => setAllCrypto(data));
        }        
        
    }, [])

    useEffect(() => {
      console.log("Internationalization switched! " + internationalization);
  
      if (internationalization === "Български") {
        setHeadlineText("Крипто-валутни цени ДНЕС благодарение на");
        setPriceText("цена");
        setNoResultsText("Няма резултати...");
      } else {
        setHeadlineText("Crypto TODAY provided by");
        setPriceText("price");
        setNoResultsText("Oops, no data yet!");
      }
      
    }, [internationalization])

  return (
    <div className="flex flex-col absolute items-center mx-[635px] border border-solid ">
      <h3 className="text-xl text-red-600 font-bold text-wrap text-center">
        {headlineText} CoinGecko API!
      </h3>
        {allCrypto?.length > 0 ? (
            allCrypto?.map((crypto: any) => (
                <div className="my-2">
        <span className="font-sans">{crypto.name.toUpperCase()} {priceText}: ${crypto.usd}</span>
      </div>
            ))
        ) : (
            <span className="font-sans">{noResultsText}</span>
        )}
    </div>
  );
};

export default CryptoBoard;
