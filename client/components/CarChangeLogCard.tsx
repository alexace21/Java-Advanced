import { CarChangeLogProps } from "@/types";
import React from "react";

interface CarChangeLogCardProps {
    car: CarChangeLogProps;
  }

const CarChangeLogCard = ({ car }: CarChangeLogCardProps) => {
  return (
    <>
      <tr className="my-paintings bg-special">
        <td scope="row">{car.id}</td>
        <td>{car.make}</td>
        <td>{car.model}</td>
        <td>{car.userOwner}</td>
        <td>${car.price}</td>
        <td>{car.submitDate}</td>
        <td>
          <button className="btn-danger btn" onClick={() => console.log("attempted delete of Registered Car!")}>
            Remove
          </button>
        </td>
      </tr>
    </>
  );
};

export default CarChangeLogCard;
