"use client";

import FeedbackForm from "@/components/FeedbackForm";
import { useAuthContext } from "@/context/AuthContext";
import { getAuthToken, getAuthUser } from "@/utils";
import { useRouter } from "next/navigation";
import React from "react";

const page = () => {
  const { role } = useAuthContext();

  const router = useRouter();

  if ( !getAuthToken() || !getAuthUser() || !role ) {
    router.push("/login");
  } else {
    return <FeedbackForm />;
  }
};

export default page;
