"use client";

import Link from "next/link";
import Image from "next/image";

import CustomButton from "./CustomButton";
import { getAuthToken } from "@/utils";
import { useEffect, useState } from "react";
import { useAuthContext } from "@/context/AuthContext";

import { jwtDecode } from "jwt-decode";
import { CustomJwtPayload } from "@/entity/CustomJwtPayload";


const Navbar = () => {
  const { isAuthenticated, setIsAuthenticated, internationalization, setInternationalization, role, setRole } = useAuthContext();

  const loggedUser = window.localStorage.getItem("auth_user");
  const isAdmin = loggedUser === "aleks.asenov@outlook.com";

  const [adminDashboardTitle, setAdminDashboardTitle] = useState("Admin Dashboard");
  const [createOfferTitle, setCreateOfferTitle] = useState("Create Offer");
  const [forSaleTitle, setForSaleTitle] = useState("For Sale");
  const [signInTitle, setSignInTitle] = useState("Sign In");
  const [logoutTitle, setLogoutTitle] = useState("Logout");

  const userLogout = () => {
    setIsAuthenticated(false);
    window.localStorage.removeItem("auth_token");
    window.localStorage.removeItem("auth_user");
  };

  const handleInternationalizationChange = (event: any) => {
    console.log(event.target.value);
    setInternationalization(event.target.value);
  };

  useEffect(() => {
    console.log("Internationalization switched! " + internationalization);

    if (internationalization === "Български") {
      setAdminDashboardTitle("Борд на Админа");
      setCreateOfferTitle("Създай оферта");
      setForSaleTitle("За продажба");
      setSignInTitle("Регистрация");
      setLogoutTitle("Излез");
    } else {
      setAdminDashboardTitle("Admin Dashboard");
      setCreateOfferTitle("Create Offer");
      setForSaleTitle("For Sale");
      setSignInTitle("Sign In");
      setLogoutTitle("Logout");
    }
    
  }, [internationalization])


  useEffect(() => {
    const token = getAuthToken();

    if (token != null) {
      setIsAuthenticated(true);

      const decoded = jwtDecode<CustomJwtPayload>(token);
      setRole(decoded.role)
    } else {
      setIsAuthenticated(false);
    }

  }, [isAuthenticated]);

  useEffect(() => {
    console.log("Rolq: " + role);
    
  }, [role])

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

        <label className="text-white" htmlFor="lang">Language</label>
                        <select
                                value={internationalization}
                                onChange={handleInternationalizationChange}
                                id="lang"
                                name="lang"
                        >
                            <option value="English">English</option>
                            <option value="Български">Български</option>
                        </select>

        {isAuthenticated && isAdmin && role === "ADMIN" && (
          <>
            <Link href="/admin-dashboard">
              <CustomButton
                title={adminDashboardTitle}
                btnType="button"
                containerStyles="text-primary-blue rounded-full bg-white min-w-[130px]"
              />
            </Link>
          </>
        )}


        {isAuthenticated && role && (
          <>
            <Link href="/create-offer">
              <CustomButton
                title={createOfferTitle}
                btnType="button"
                containerStyles="text-primary-blue rounded-full bg-white min-w-[130px]"
              />
            </Link>

            <Link href="/for-sale">
              <CustomButton
                title={forSaleTitle}
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
                title={signInTitle}
                btnType="button"
                containerStyles="text-primary-blue rounded-full bg-white min-w-[130px]"
              />
            </Link>
          </>
        )}

        {isAuthenticated && role && (
          <>
            <CustomButton
              title={logoutTitle}
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
