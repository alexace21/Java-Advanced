"use client";

import { registerUser } from "@/utils";
import { useEffect, useState } from "react";
import CustomButton from "./CustomButton";
import { useRouter } from "next/navigation";
import Link from "next/link";
import { useAuthContext } from "@/context/AuthContext";

const RegistrationForm: React.FC = () => {
  const { internationalization } = useAuthContext();
  const [existingAccountText, setExistingAccountText] = useState("Already have an account?");
  const [loginText, setLoginText] = useState("Login");
  const [createUserTitle, setCreateUserTitle] = useState("Create user");

  const [firstNameText, setFirstNameText] = useState("First Name");
  const [lastNameText, setLastNameText] = useState("Last Name");
  const [emailText, setEmailText] = useState("Email");
  const [passwordText, setPasswordText] = useState("Password");


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


  useEffect(() => {
    console.log("Internationalization switched! " + internationalization);

    if (internationalization === "Български") {
      setExistingAccountText("Имате регистрация?")
      setLoginText("Вход");
      setCreateUserTitle("Създайте потребител");

      setEmailText("Електронна Поща");
      setPasswordText("Парола");
      setFirstNameText("Първо име");
      setLastNameText("Фамилия");
    } else {
      setExistingAccountText("Already have an account?");
      setLoginText("Login");
      setCreateUserTitle("Create user");

      setEmailText("Email");
      setPasswordText("Password");
      setFirstNameText("First Name");
      setLastNameText("Last Name");
    }
    
  }, [internationalization])

  return (
    <form action={"/register"} className="registration-form">
      <div>
        <label htmlFor="email" typeof="text" aria-placeholder="Email" />
        <input
          type="email"
          placeholder={emailText}
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="password" typeof="text" aria-placeholder="Password" />
        <input
          type="password"
          placeholder={passwordText}
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="firstName" typeof="text" aria-placeholder="First Name" />
        <input
          type="firstName"
          placeholder={firstNameText}
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="lastName" typeof="text" aria-placeholder="Last Name" />
        <input
          type="lastName"
          placeholder={lastNameText}
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
        />
      </div>

      <div>
        <span className="font-bold ml-5 mr-5">{existingAccountText}</span>
        <Link href="/login">
          <button
            className="text-blue-700 font-semibold"
            type="button"
          >{loginText}</button>
        </Link>
      </div>

      {registrationError && <p>{registrationError}</p>}
      <div className="items-center px-4">
        <CustomButton
          title={createUserTitle}
          btnType="button"
          containerStyles="bg-blue-500 text-white rounded-md hover:bg-blue-600 transition duration-300 ease-in-out min-w-[400px]"
          handleClick={handleRegistration}
        />
      </div>
    </form>
  );
};

export default RegistrationForm;
