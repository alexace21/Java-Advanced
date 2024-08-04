"use client";

import { registerUser } from "@/utils";
import { useState } from "react";
import CustomButton from "./CustomButton";
import { useRouter } from "next/navigation";
import Link from "next/link";

const RegistrationForm: React.FC = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");

  const [registrationError, setRegistrationError] = useState<string | null>(
    null
  );

  const router = useRouter();

  const handleRegistration = async () => {
    try {
      const result = await registerUser(email, password, firstName, lastName);

      if (result == 200) {
        router.push("/login");
      } else {
        setRegistrationError(result); // Customize error message
      }
      
    } catch (error) {
      setRegistrationError("Something went wrong. Please try again."); // Customize error message
    }
  };

  return (
    <form action={"/register"} className="registration-form">
      <div>
        <label htmlFor="email" typeof="text" aria-placeholder="Email" />
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="password" typeof="text" aria-placeholder="Password" />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="firstName" typeof="text" aria-placeholder="First Name" />
        <input
          type="firstName"
          placeholder="Fisrt Name"
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="lastName" typeof="text" aria-placeholder="Last Name" />
        <input
          type="lastName"
          placeholder="Last Name"
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
        />
      </div>

      <div>
        <span className="font-bold ml-5 mr-5">Already have an account?</span>
        <Link href="/login">
          <button
            className="text-blue-700 font-semibold"
            type="button"
          >Login</button>
        </Link>
      </div>

      {registrationError && <p>{registrationError}</p>}
      <div className="items-center px-4">
        <CustomButton
          title="Create user"
          btnType="button"
          containerStyles="bg-blue-500 text-white rounded-md hover:bg-blue-600 transition duration-300 ease-in-out min-w-[400px]"
          handleClick={handleRegistration}
        />
      </div>
    </form>
  );
};

export default RegistrationForm;
