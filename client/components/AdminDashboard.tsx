"use client";

import { fetchCarsChangeLog } from "@/utils";
import { useEffect, useState } from "react";
import UserFeedbackCard from "./UserFeedbackCard";

interface AdminBoardProps {
  user: string | null;
}

const AdminDashboard = ({ user }: AdminBoardProps) => {
  const [allCars, setAllCars] = useState([]);
  const [allUsers, setAllUsers] = useState([]);
  const [allFeedbacks, setAllFeedbacks] = useState([]);

  const fetchAllRegisteredCars = async () => {
    const result = await fetchCarsChangeLog();

    if (result) {
      setAllFeedbacks(result);
    }
  };

  useEffect(() => {
    fetchAllRegisteredCars();
  }, []);

  return (
    <>
      <main className="mb-5 pt-16">
        <div className="flex flex-col absolute items-center ml-[28px] mt-28 border border-solid">
          <h3 className="text-xl text-red-600 font-bold">
            Prices today by CoinGecko API!
          </h3>
          <span className="text-xl">~~~~~~~~~~~~~~~~~~~</span>
          <div className="my-2">
            <span>XRP price: ${0.57}</span>
          </div>

          <div>
            <span>XDC price: ${0.027}</span>
          </div>
        </div>

        <div className="welcome mt-1 mb-4">
          <p className="mySticky bg-gray text-dark user text-2xl">
            Welcome <span className="text-blue-400">{user}</span>
          </p>
          <div className="row mt-1 mb-2 justify-center">
            <div className="col-6 mt-1 mb-1 mr-10 w-[820px]">
              <div>
                <div className="home-fields section-height overflow-auto">
                  <h3 className="my-paintings bg-slate-800">Registered Cars</h3>
                  <table className="table">
                    <tr className="my-paintings h-8 bg-special">
                      <td scope="row">id</td>
                      <td>Make</td>
                      <td>Model</td>
                      <td>Owner</td>
                      <td>Price</td>
                      <td>Date</td>
                      <td>Remove</td>
                    </tr>
                  </table>
                  <table className="table table-striped table-dark">
                    <tr className="my-paintings bg-special">
                      <td scope="row">id</td>
                      <td>{"Toyota"}</td>
                      <td>{"Corolla"}</td>
                      <td>{"aleks.asenov@outlook.com"}</td>
                      <td>${"5000"}</td>
                      <td>{"8/3/2024"}</td>
                      <td>
                        <a className="btn-danger btn" href="">
                          Remove
                        </a>
                      </td>
                    </tr>
                  </table>
                </div>
              </div>

              <div className="home-fields section-height overflow-auto">
                <h3 className="my-paintings bg-slate-800">Registered Users</h3>
                <table className="table">
                  <tr className="my-paintings h-8 bg-special">
                    <td>Email</td>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Date</td>
                    <td>Role</td>
                  </tr>
                </table>
                <table className="table table-striped table-dark">
                  <tr className="my-paintings bg-special">
                    <td>axelFoli@abv.bg</td>
                    <td>Axel</td>
                    <td>Fooli</td>
                    <td>8/3/2024</td>
                    <td>Admin</td>
                  </tr>
                </table>
              </div>
            </div>

            <div className="col-6 mt-1 mb-1">
              <div className="home-fields h-full w-[550px]">
                <h3 className="my-paintings bg-slate-800">User Feedback</h3>
                <ul className="list-group list-group-vertical text-dark">
                {allFeedbacks.length > 0 ? (
                allFeedbacks.map((feedback: any) => (
                    <UserFeedbackCard feedback={feedback} />
                ))
                ) : (
                <span>Oops, no results yet!</span>
                )}
                </ul>
              </div>
            </div>

            {/* <div className="col-6 mt-1 mb-1">
              <div className="home-fields h-full w-[550px]">
                <h3 className="my-paintings bg-slate-800">User Feedback</h3>
                <ul className="list-group list-group-vertical text-dark">
                  <li>
                    <div className="my-paintings-first-row bg-special">
                      <p>Id: id</p>
                      <p>Status: NEW</p>
                      <p>Owner: Axel Foli</p>
                      <p>Satisfaction: Satisfied</p>
                      <p>Recommend: Probably</p>
                      <div className="buttons-info"></div>
                    </div>
                    <div className="buttons-info">
                      <div className="favorite">
                        <p>
                          <a className="btn-info btn" href="/">
                            Resolve
                          </a>
                        </p>
                      </div>
                      <div className="rate">
                        <p>
                          <a className="btn-primary btn" href="/">
                            Delete
                          </a>
                        </p>
                      </div>
                    </div>
                    <div className="second-info">
                      <p>Date: 8/3/2024</p>
                      <p>Added by axelPz@abv.bg</p>
                    </div>
                  </li>
                </ul>
              </div>
            </div> */}
          </div>
        </div>
      </main>
    </>
  );
};

export default AdminDashboard;
