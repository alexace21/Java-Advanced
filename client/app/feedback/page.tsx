"use client";

import FeedbackForm from "@/components/FeedbackForm";
import { getAuthToken, getAuthUser } from "@/utils";
import { useRouter } from "next/navigation";
import React from "react";

const page = () => {
  const router = useRouter();

  if (!getAuthToken() || !getAuthUser()) {
    router.push("/login");
  } else {
    return <FeedbackForm />;
  }
};

export default page;
