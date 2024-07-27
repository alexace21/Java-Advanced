"use client";

import Image from "next/image";
import { useState } from "react";
import { CarCardProps } from "@/types";
import CustomButton from "./CustomButton";

interface CarProps {
    car: CarCardProps
}

const CarCard = ({ car }: CarProps) => {
  return (
    <div>CarCard</div>
  )
}

export default CarCard