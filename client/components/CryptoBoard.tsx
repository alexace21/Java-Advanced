"use client";

import { fetchAllCryptoData } from "@/utils";
import React, { useEffect, useState } from "react";

const CryptoBoard = () => {

    const [allCrypto, setAllCrypto] = useState([]);


    useEffect(() => {
        const result = fetchAllCryptoData();

        if (result) {   
            result.then(data => setAllCrypto(data));
        }        
        
    }, [])


  return (
    <div className="flex flex-col absolute items-center mx-[635px] border border-solid">
      <h3 className="text-xl text-red-600 font-bold">
        Prices today by CoinGecko API!
      </h3>
      <span className="text-xl">~~~~~~~~~~~~~~~~~~~</span>
        {allCrypto.length > 0 ? (
            allCrypto.map((crypto: any) => (
                <div className="my-2">
        <span className="font-sans">{crypto.name.toUpperCase()} price: ${crypto.usd}</span>
      </div>
            ))
        ) : (
            <span className="font-sans">Oops, no data yet!</span>
        )}
    </div>
  );
};

export default CryptoBoard;
