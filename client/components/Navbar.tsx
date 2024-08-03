"use client";

import Link from "next/link";
import Image from "next/image";

import CustomButton from "./CustomButton";
import { getAuthToken } from "@/utils";
import { useEffect } from "react";
import { useAuthContext } from "@/context/AuthContext";
const Navbar = () => {
  const { isAuthenticated, setIsAuthenticated } = useAuthContext();
  const loggedUser = window.localStorage.getItem("auth_user");
    
  const isAdmin = loggedUser === "axelPz@abv.bg";

  const userLogout = () => {
    setIsAuthenticated(false);
    window.localStorage.removeItem("auth_token");
    window.localStorage.removeItem("auth_user");
  };

  useEffect(() => {
    const auth = getAuthToken();

    console.log(auth);
    if (auth) {
      setIsAuthenticated(true);
    } else {
      setIsAuthenticated(false);
    }

    console.log(isAuthenticated);
  }, [isAuthenticated]);

  return (
    <header className="w-full absolute z-10">
      <nav className="max-w-[1440px] mx-auto flex justify-between items-center sm:px-16 px-6 py-4">
        <Link href="/" className="flex justify-center items-center">
          <Image
            src="/logo.svg"
            alt="72cars Logo"
            width={118}
            height={18}
            className="object-contain"
          />
        </Link>

        {isAuthenticated && isAdmin && (
          <>
            <Link href="/admin-dashboard">
              <CustomButton
                title="Admin Dashboard"
                btnType="button"
                containerStyles="text-primary-blue rounded-full bg-white min-w-[130px]"
              />
            </Link>
          </>
        )}


        {isAuthenticated && (
          <>
            <Link href="/create-offer">
              <CustomButton
                title="Create Offer"
                btnType="button"
                containerStyles="text-primary-blue rounded-full bg-white min-w-[130px]"
              />
            </Link>

            <Link href="/for-sale">
              <CustomButton
                title="For SALE"
                btnType="button"
                containerStyles="text-primary-blue rounded-full bg-white min-w-[130px]"
              />
            </Link>
          </>
        )}

        {!isAuthenticated && (
          <>
            <Link href="/register">
              <CustomButton
                title="Sign In"
                btnType="button"
                containerStyles="text-primary-blue rounded-full bg-white min-w-[130px]"
              />
            </Link>
          </>
        )}

        {isAuthenticated && (
          <>
            <CustomButton
              title="Logout"
              btnType="button"
              containerStyles="text-primary-blue rounded-full bg-white min-w-[130px]"
              handleClick={userLogout}
            />
          </>
        )}
      </nav>
    </header>
  );
};

export default Navbar;
