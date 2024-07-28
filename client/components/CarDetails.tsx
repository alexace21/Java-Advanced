import Image from "next/image";
import { Fragment } from "react";

import { Dialog, Transition } from "@headlessui/react";

import { CarCardProps } from "@/types";

interface CarDetailsProps {
    isOpen: boolean;
    closeModal: () => void;
    car: CarCardProps;
}

const CarDetails = ({ isOpen, closeModal, car }: CarDetailsProps) => {
  return (
    <div>CarDetails</div>
  )
}

export default CarDetails