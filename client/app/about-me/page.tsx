"use client";

import { useAuthContext } from "@/context/AuthContext";
import Image from "next/image";
import Link from "next/link";
import React, { useEffect, useState } from "react";

const page = () => {
  const { internationalization } = useAuthContext();
  const [helloText, setHelloText] = useState("Hello, I am");
  const [Name, setName] = useState("Alex Asenov");
  const [introText, setIntroText] = useState("I am Software (engineer) enthusiast with Over 5 years of Personal experience & 2 Professional");
  const [comanyText, setComanyText] = useState("CommerzBank AG - Germany");
  const [learnMoreText, setLearnMoreText] = useState("Learn More About me.");
  const [descriptionText, setDescriptionText] = useState("In my leisure time, I enjoy driving my Mercedes CLK 270, travel and" +
            "see the World from different angles. Other hobbies of mine are to" +
            "explore other cultures, cuisine, places I have never been to and" +
            "meet with diverse range of people. I enjoy reading as much as my" +
            "coffee in the morning. Sports is my nature. Exercising not only in" +
            "the gym but also on the field regardles of the sport makes me" +
            "connect better with my inner self and elevate to higher standards")

  useEffect(() => {
    console.log("Internationalization switched! " + internationalization);

    if (internationalization === "Български") {
      setHelloText("Здравейте, Аз съм");
      setName("Алекс Асенов");
      setIntroText("Аз съм Софтуерен (инженер) ентусиаст с повече от 5 години Личен опит, от които 2 ПРОФесионален");
      setComanyText("КомерцБанк АГ - Германия");
      setLearnMoreText("Научете повече за мен тук.")
      setDescriptionText(
            "В свободното си време обичам да карам моя автомобил Mercedes CLK 270, да пътувам и да" +
            "виждам света от различни ъгли. Другите ми хобита включват изследване на" +
            "различни култури, кухни, места, на които никога не съм бил, и срещи с" +
            "разнообразни хора. Обичам да чета колкото и да обичам кафето сутрин. Спортът" +
            "е част от мен. Тренирам не само в залата, но и на терена, независимо от" +
            "спорта, което ме свързва със себе си и ме издига на по-високи стандарти"
      )
    } else {
      setHelloText("Hello, I am");
      setName("Alex Asenov");
      setIntroText("I am Software (engineer) enthusiast with Over 5 years of Personal experience & 2 Professional");
      setComanyText("CommerzBank AG - Germany");
      setLearnMoreText("Learn More About me");
      setDescriptionText(
            "In my leisure time, I enjoy driving my Mercedes CLK 270, travel and" +
            "see the World from different angles. Other hobbies of mine are to" +
            "explore other cultures, cuisine, places I have never been to and" +
            "meet with diverse range of people. I enjoy reading as much as my" +
            "coffee in the morning. Sports is my nature. Exercising not only in" +
            "the gym but also on the field regardles of the sport makes me" +
            "connect better with my inner self and elevate to higher standards"
          );
    }
    
  }, [internationalization])

  return (
    <section className="pt-32">
      <div className="container mx-auto flex px-10 md:flex-row flex-col items-center">
        {/* Your content goes here */}
        <h1 className="title-font sm:text-4xl text-3xl mb-4 font-medium text-black">
          {helloText} <span className="text-blue-500">{Name}</span>.
        </h1>
        <div className="ml-5">
          <span className="font-serif font-semibold text-xl">
            {introText} <span className="text-yellow-400">@ {comanyText}</span>
          </span>
        </div>
      </div>

      {/* Other elements */}
      <div className="container mx-auto flex px-10 py-20 md:flex-row flex-col items-center">
        <div className="flex-grow">
          <Image
            src="/aboutMe.jpg"
            alt="Image of Alex"
            width={300}
            height={300}
            className="object-contain profile-image"
          />
        </div>

        <div className="hobbies-section ml-auto">
          <p>
            {descriptionText}
          </p>

          <Link
            href="https://3aportfolio.com/"
            className="font-bold text-yellow-400 text-center hover:underline py-5 text-4xl"
          >
            {learnMoreText}
          </Link>
        </div>
      </div>
    </section>
  );
};

export default page;
