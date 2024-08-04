import React from "react";

const CryptoBoard = () => {
  return (
    <div className="flex flex-col absolute items-center mx-[635px] border border-solid">
      <h3 className="text-xl text-red-600 font-bold">
        Prices today by CoinGecko API!
      </h3>
      <span className="text-xl">~~~~~~~~~~~~~~~~~~~</span>
      <div className="my-2">
        <span className="font-sans">XRP price: ${0.57}</span>
      </div>

      <div>
        <span className="font-sans">XDC price: ${0.027}</span>
      </div>
    </div>
  );
};

export default CryptoBoard;
