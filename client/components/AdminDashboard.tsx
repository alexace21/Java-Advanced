"use client";

import { fetchAllFeedbackChangeLog, removeUserFeedback, fetchAllRegisteredUsers, fetchAllRegisteredCars } from "@/utils";
import { useEffect, useState } from "react";
import UserFeedbackCard from "./UserFeedbackCard";
import UserChangeLogCard from "./UserChangeLogCard";
import CarChangeLogCard from "./CarChangeLogCard";
import CryptoBoard from "./CryptoBoard";
import { useAuthContext } from "@/context/AuthContext";
import { useRouter } from "next/navigation";

interface AdminBoardProps {
  user: string | null;
}

const AdminDashboard = ({ user }: AdminBoardProps) => {
  const { internationalization, setIsAuthenticated } = useAuthContext();
  const [welcomeText, setWelcomeText] = useState("Welcome");
  const [carsLogText, setCarsLogText] = useState("Cars Change Log");
  const [usersLogText, setUsersLogText] = useState("Users Change Log");
  const [userFeedbackText, setUserFeedbackText] = useState("User Feedback");
  const [noResultsText, setNoResultsText] = useState("Oops, no results yet!");

  const [emailText, setEmailText] = useState("Email");
  const [firstNameText, setFirstNameText] = useState("First Name");
  const [lastNameText, setLastNameText] = useState("Last Name");
  const [dateText, setDateText] = useState("Date");
  const [roleText, setRoleText] = useState("Role")

  const [makeText, setMakeText] = useState("Make");
  const [modelText, setModelText] = useState("Model");
  const [ownerText, setOwnerText] = useState("Owner");
  const [priceText, setPriceText] = useState("Price");
  const [statusText, setStatusText] = useState("Status")

  const router = useRouter();


  useEffect(() => {
    console.log("Internationalization switched! " + internationalization);

    if (internationalization === "Български") {
      setWelcomeText("Добре дошли");
      setCarsLogText("Промени на автомобилите")
      setUsersLogText("Промени на потребителите");
      setUserFeedbackText("Обратна връзка от потребителите");
      
      setEmailText("Електронна Поща");
      setFirstNameText("Първо име");
      setLastNameText("Фамилия");
      setDateText("Дата");
      setRoleText("Права");

      setMakeText("Марка");
      setModelText("Модел");
      setOwnerText("Собственик");
      setPriceText("Цена");
      setStatusText("Статут");

      setNoResultsText("Няма резултати...")
    } else {
      setWelcomeText("Welcome");
      setCarsLogText("Cars Change Log")
      setUsersLogText("Users Change Log");
      setUserFeedbackText("User Feedback");

      setEmailText("Email");
      setFirstNameText("First Name");
      setLastNameText("Last Name");
      setDateText("Date");
      setRoleText("Role");

      setMakeText("Make");
      setModelText("Model");
      setOwnerText("Owner");
      setPriceText("Price");
      setStatusText("Status");
      
      setNoResultsText("Oops, no results yet!")
    }
    
  }, [internationalization])

  const [allCars, setAllCars] = useState([]);
  const [allUsers, setAllUsers] = useState([]);
  const [allFeedbacks, setAllFeedbacks] = useState([]);

  const fetchFeedbackLogs = async () => {
    const result = await fetchAllFeedbackChangeLog();

    if (result === "Unauthorized path") {
      setIsAuthenticated(false);
      window.localStorage.removeItem("auth_token");
      window.localStorage.removeItem("auth_user");
      alert("Login has expired!");
      router.push("/login");
    }

    if (result) {
      setAllFeedbacks(result);
    }
  };

  const fetchRegisteredUserLogs = async () => {
    const result = await fetchAllRegisteredUsers();

    if (result === "Unauthorized path") {
      setIsAuthenticated(false);
      window.localStorage.removeItem("auth_token");
      window.localStorage.removeItem("auth_user");
      alert("Login has expired!");
      router.push("/login");
    }
    
    if (result) {
        setAllUsers(result);
    }
  };

  const fetchRegisteredCarLogs = async () => {
    const result = await fetchAllRegisteredCars();
    
    if (result === "Unauthorized path") {
      setIsAuthenticated(false);
      window.localStorage.removeItem("auth_token");
      window.localStorage.removeItem("auth_user");
      alert("Login has expired!");
      router.push("/login");
    }

    if (result) {
        setAllCars(result);
    }
  };

  const handleDeleteUserFeedback = async (id: string) => {
    const result = await removeUserFeedback(id);

        if (result) {
            setAllFeedbacks(allFeedbacks.filter((feedback: any) => feedback.id != result.id))
        } else {
            alert("Something went wrong with removing Feedback id: " + id + ". Please try again later!");
        }
  }

  useEffect(() => {
    fetchFeedbackLogs();
    fetchRegisteredUserLogs();
    fetchRegisteredCarLogs();
  }, []);



  return (
    <>
      <main className="mb-5 pt-16">
        <CryptoBoard />
        <div className="welcome mt-1 mb-4">
          <p className="mySticky bg-gray text-dark user text-2xl">
            {welcomeText}: <span className="text-blue-400">{user}</span>
          </p>
          <div className="row mt-1 mb-2 justify-center">
            <div className="col-6 mt-1 mb-1 mr-10 w-[820px]">
              <div>
                <div className="home-fields section-height overflow-auto">
                  <h3 className="my-paintings bg-slate-800">{carsLogText}</h3>
                  <table className="table">
                    <tr className="my-paintings h-8 bg-special">
                      <td scope="row">id</td>
                      <td>{makeText}</td>
                      <td>{modelText}</td>
                      <td>{ownerText}</td>
                      <td>{priceText}</td>
                      <td>{dateText}</td>
                      <td>{statusText}</td>
                    </tr>
                  </table>
                  <table className="table table-striped table-dark">
                    {allCars?.length > 0 ? (
                        allCars?.map((car: any) => (
                            <CarChangeLogCard car={car} />
                        ))
                    ) : (
                        <span>{noResultsText}</span> 
                    )}
                  </table>
                </div>
              </div>

              <div className="home-fields section-height overflow-auto">
                <h3 className="my-paintings bg-slate-800">{usersLogText}</h3>
                <table className="table">
                  <tr className="my-paintings h-8 bg-special">
                    <td>{emailText}</td>
                    <td>{firstNameText}</td>
                    <td>{lastNameText}</td>
                    <td>{dateText}</td>
                    <td>{roleText}</td>
                  </tr>
                </table>
                <table className="table table-striped table-dark">
                    {allUsers?.length > 0 ? (
                        allUsers?.map((user: any) => (
                            <UserChangeLogCard user={user}/>
                        )) 
                    ): (
                        <span>{noResultsText}</span> 
                    )}
                </table>
              </div>
            </div>

            <div className="col-6 mt-1 mb-1">
              <div className="home-fields h-full w-[550px]">
                <h3 className="my-paintings bg-slate-800">{userFeedbackText}</h3>
                <ul className="list-group list-group-vertical text-dark">
                {allFeedbacks?.length > 0 ? (
                allFeedbacks?.map((feedback: any) => (
                    <UserFeedbackCard feedback={feedback} handleRemoveFeedback={handleDeleteUserFeedback}/>
                ))
                ) : (
                    <span>{noResultsText}</span>
                )}
                </ul>
              </div>
            </div>
          </div>
        </div>
      </main>
    </>
  );
};

export default AdminDashboard;
