import { CarChangeLogProps } from "@/types";
import React from "react";

interface CarChangeLogCardProps {
    car: CarChangeLogProps;
  }

const CarChangeLogCard = ({ car }: CarChangeLogCardProps) => {
  return (
    <>
      <tr className="my-paintings bg-special">
        <td scope="row" className="box-table">{car.id}</td>
        <td className="box-table">{car.make}</td>
        <td className="box-table">{car.model}</td>
        <td className="box-table">{car.userOwner}</td>
        <td className="box-table">${car.price}</td>
        <td className="box-table">{car.submitDate}</td>
        <td className="box-table">{car.status}</td>
      </tr>
    </>
  );
};

export default CarChangeLogCard;
