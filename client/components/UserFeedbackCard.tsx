"use client";

import { FeedbackCardProps } from "@/types";
import { resolveFeedback } from "@/utils";
import React, { useState } from "react";

interface FeedbackProps {
  feedback: FeedbackCardProps;
  handleRemoveFeedback: (id: string) => void;
}

const UserFeedbackCard = ({ handleRemoveFeedback, feedback }: FeedbackProps) => {

    const [status, setStatus] = useState(feedback.status);

    const handleResolveFeedback = async(id: string) => {
        const result = await resolveFeedback(id);

        if (result) {
            setStatus(result.status);
        } else {
            alert("Something went wrong with resolving Feedback id: " + id + ". Please try again later!");
        }
        
    }

  return (
    <>
      <li>
        <div className="my-paintings-first-row bg-special">
          <p>Id: {feedback.id}</p>
          <p>Status: {status}</p>
          <p>
            Owner: {feedback.firstName} {feedback.lastName}
          </p>
          <p>Satisfaction: {feedback.satisfaction}</p>
          <p>Recommend: {feedback.recommendation}</p>
          <div className="buttons-info"></div>
        </div>
        <div className="buttons-info">
          <div className="favorite">
            <p>
              <button className="btn-info btn" onClick={() => handleResolveFeedback(feedback.id)}>
                Resolve
              </button>
            </p>
          </div>
          <div className="rate">
            <p>
              <button className="btn-primary btn" onClick={() => handleRemoveFeedback(feedback.id)}>
                Delete
              </button>
            </p>
          </div>
        </div>
        <div className="second-info">
          <p>Date: {feedback.submitDate}</p>
          <p>Added by: {feedback.ownerEmail}</p>
        </div>
      </li>
    </>
  );
};

export default UserFeedbackCard;
