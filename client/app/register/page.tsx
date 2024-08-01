"use client";

import RegistrationForm from "@/components/RegistrationForm";
import { useAuthContext } from "@/context/AuthContext";
import { useRouter } from "next/navigation";

const RegisterPage = () => {

  const {isAuthenticated, setIsAuthenticated} = useAuthContext();
  const router = useRouter();

  if (isAuthenticated) {
    router.push("/");
  }

  return (
    <main className="overflow-hidden">
      <div className="">
        <h1 className="text-center text-4xl font-extrabold pt-32">Register</h1>
        <RegistrationForm />
      </div>
    </main>
  );
};

export default RegisterPage;
