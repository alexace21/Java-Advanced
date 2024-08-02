"use client";

import FeedbackForm from "@/components/FeedbackForm";
import { useAuthContext } from "@/context/AuthContext";
import { useRouter } from "next/navigation";
import React from "react";

const page = () => {

  const {isAuthenticated, setIsAuthenticated} = useAuthContext();
  const router = useRouter();

  if (!isAuthenticated) {
    router.push("/login");
  }

  return <FeedbackForm />;
};

export default page;
