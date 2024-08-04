import { UserChangeLogProps } from "@/types";
import React from "react";


interface UserChangeLogCardProps {
    user: UserChangeLogProps;
  }

const UserChangeLogCard = ({ user }: UserChangeLogCardProps) => {
  return (
    <>
      <tr className="my-paintings bg-special">
        <td>{user.email}</td>
        <td>{user.firstName}</td>
        <td>{user.lastName}</td>
        <td>{user.registeredDate}</td>
        <td>{user.role}</td>
      </tr>
    </>
  );
};

export default UserChangeLogCard;
