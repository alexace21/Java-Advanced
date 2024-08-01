import Image from "next/image";
import Link from "next/link";
import React from "react";

const page = () => {
  return (
    <section className="pt-32">
      <div className="container mx-auto flex px-10 md:flex-row flex-col items-center">
        {/* Your content goes here */}
        <h1 className="title-font sm:text-4xl text-3xl mb-4 font-medium text-black">
          Hello, I am <span className="text-blue-500">Alex Asenov</span>!
        </h1>
        <div className="ml-5">
          <span className="font-serif font-semibold text-xl">
            I am Software (engineer) enthusiast with Over 5 years of Personal
            experience & 2 Professional @ CommerzBank AG - Germany
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
            In my leisure time, I enjoy driving my Mercedes CLK 270, travel and
            see the World from different angles. Other hobbies of mine are to
            explore other cultures, cuisine, places I have never been to and
            meet with diverse range of people. I enjoy reading as much as my
            coffee in the morning. Sports is my nature. Exercising not only in
            the gym but also on the field regardles of the sport makes me
            connect better with my inner self and elevate to higher standards.
          </p>

          <Link
            href="https://3aportfolio.com/"
            title="Learn more"
            className="font-bold text-yellow-400 text-center hover:underline py-5 text-4xl"
          >
            Learn More About me
          </Link>
        </div>
      </div>
    </section>
  );
};

export default page;
