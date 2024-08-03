"use client";

import AdminDashboard from "@/components/AdminDashboard";
import { useAuthContext } from "@/context/AuthContext";
import { useRouter } from "next/navigation";
import React from "react";

const page = () => {
  const { isAuthenticated, setIsAuthenticated } = useAuthContext();
  const router = useRouter();
  const loggedUser = window.localStorage.getItem("auth_user");

  const isAdmin = loggedUser === "aleks.asenov@outlook.com";

  if (!isAuthenticated && !isAdmin) {
    router.push("/");
  }

  return <AdminDashboard user={loggedUser} />;
};

export default page;
