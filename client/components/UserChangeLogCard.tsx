import { UserChangeLogProps } from "@/types";
import React from "react";


interface UserChangeLogCardProps {
    user: UserChangeLogProps;
  }

const UserChangeLogCard = ({ user }: UserChangeLogCardProps) => {
  return (
    <>
      <tr className="my-paintings bg-special">
        <td className="box-table">{user.email}</td>
        <td className="box-table">{user.firstName}</td>
        <td className="box-table">{user.lastName}</td>
        <td className="box-table">{user.registeredDate}</td>
        <td className="box-table">{user.role}</td>
      </tr>
    </>
  );
};

export default UserChangeLogCard;
