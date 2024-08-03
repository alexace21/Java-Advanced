import { FeedbackCardProps } from "@/types";
import React from "react";

interface FeedbackProps {
  feedback: FeedbackCardProps;
}

const UserFeedbackCard = ({ feedback }: FeedbackProps) => {
  return (
    <>
      <li>
        <div className="my-paintings-first-row bg-special">
          <p>Id: {feedback.id}</p>
          <p>Status: {feedback.status}</p>
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
          <p>Date: {feedback.submitDate}</p>
          <p>Added by: {feedback.ownerEmail}</p>
        </div>
      </li>
    </>
  );
};

export default UserFeedbackCard;
