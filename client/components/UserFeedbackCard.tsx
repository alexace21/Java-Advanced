"use client";

import { useAuthContext } from "@/context/AuthContext";
import { FeedbackCardProps } from "@/types";
import { resolveFeedback } from "@/utils";
import { useRouter } from "next/navigation";
import React, { useEffect, useState } from "react";

interface FeedbackProps {
  feedback: FeedbackCardProps;
  handleRemoveFeedback: (id: string) => void;
}

const UserFeedbackCard = ({ handleRemoveFeedback, feedback }: FeedbackProps) => {
    const { internationalization, setIsAuthenticated } = useAuthContext();
    const router = useRouter();

    const [statusText, setStatusText] = useState("Status");
    const [ownerText, setOwnerText] = useState("Owner");
    const [satisfactionText, setSatisfactionText] = useState("Satisfaction");
    const [recommendText, setRecommendText] = useState("Recommend");
    const [dateText, setDateText] = useState("Date");
    const [addByText, setAddByText] = useState("Added by");
    const [resolveText, setResolveText] = useState("Resolve");
    const [deleteText, setDeleteText] = useState("Delete")


    const [status, setStatus] = useState(feedback.status);

    const handleResolveFeedback = async(id: string) => {
        const result = await resolveFeedback(id);

        if (result === 403) {
          setIsAuthenticated(false);
          window.localStorage.removeItem("auth_token");
          window.localStorage.removeItem("auth_user");
          alert("You don't have access to this resource!");
          router.push("/");
    
          return;
        }

        if (result) {
            setStatus(result.status);
        } else {
            alert("Something went wrong with resolving Feedback id: " + id + ". Please try again later!");
        }
        
    }

    useEffect(() => {
      console.log("Internationalization switched! " + internationalization);
  
      if (internationalization === "Български") {
        setStatusText("Статут");
        setOwnerText("Собственик");
        setSatisfactionText("Удовлетворение");
        setRecommendText("Препоръчвам");
        setDateText("Дата");
        setAddByText("Добавен от");
        setResolveText("Решаване");
        setDeleteText("Изтриване")
      } else {
        setStatusText("Status");
        setOwnerText("Owner");
        setSatisfactionText("Satisfaction");
        setRecommendText("Recommend");
        setDateText("Date");
        setAddByText("Added by");
        setResolveText("Resolve");
        setDeleteText("Delete")
      }
      
    }, [internationalization])

  return (
    <>
      <li>
        <div className="my-paintings-first-row bg-special">
          <p>Id: {feedback.id}</p>
          <p>{statusText}: {status}</p>
          <p>
            {ownerText}: {feedback.firstName} {feedback.lastName}
          </p>
          <p>{satisfactionText}: {feedback.satisfaction}</p>
          <p>{recommendText}: {feedback.recommendation}</p>
          <div className="buttons-info"></div>
        </div>
        <div className="buttons-info">
          <div className="favorite">
            <p>
              <button className="btn-info btn" onClick={() => handleResolveFeedback(feedback.id)}>
                {resolveText}
              </button>
            </p>
          </div>
          <div className="rate">
            <p>
              <button className="btn-primary btn" onClick={() => handleRemoveFeedback(feedback.id)}>
                {deleteText}
              </button>
            </p>
          </div>
        </div>
        <div className="second-info">
          <p>{dateText}: {feedback.submitDate}</p>
          <p>{addByText}: {feedback.ownerEmail}</p>
        </div>
      </li>
    </>
  );
};

export default UserFeedbackCard;
