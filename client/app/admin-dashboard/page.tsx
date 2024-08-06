"use client";

import AdminDashboard from "@/components/AdminDashboard";
import { useAuthContext } from "@/context/AuthContext";
import { getAuthToken, getAuthUser } from "@/utils";
import { useRouter } from "next/navigation";
import React from "react";

const page = () => {
  const { role } = useAuthContext();
  const router = useRouter();
  const loggedUser = window.localStorage.getItem("auth_user");

  const isAdmin = loggedUser === "aleks.asenov@outlook.com";

  if (!getAuthToken() || !getAuthUser() || !isAdmin || role !== "ADMIN") {
    router.push("/");
  } else {
    return <AdminDashboard user={loggedUser} />;
  }
};

export default page;
