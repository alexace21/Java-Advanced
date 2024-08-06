"use client";

import { useAuthContext } from "@/context/AuthContext";
import { fetchAllCryptoData } from "@/utils";
import { useRouter } from "next/navigation";
import React, { useEffect, useState } from "react";

const CryptoBoard = () => {
    const { internationalization, setIsAuthenticated } = useAuthContext();
    const router = useRouter();

    const [headlineText, setHeadlineText] = useState("Crypto TODAY provided by");
    const [priceText, setPriceText] = useState("price");
    const [noResultsText, setNoResultsText] = useState("Oops, no data yet!")

    const [allCrypto, setAllCrypto] = useState(Array);

    const getCrypto = async() => {
        const result = await fetchAllCryptoData();

        if (result === 403) {
          setIsAuthenticated(false);
          window.localStorage.removeItem("auth_token");
          window.localStorage.removeItem("auth_user");
          alert("You don't have access to this resource!");
          router.push("/");
    
          return;
        }

        if (result instanceof Array) {   
            setAllCrypto(result);
        }  else {
          setAllCrypto([]);
          alert("Something went wrong with fetching Crypto prices!");
        }
        
    };

    useEffect(() => {
        getCrypto();     
        
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
