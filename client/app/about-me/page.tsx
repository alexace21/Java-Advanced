import { Description } from "@headlessui/react";
import React from "react";

const page = () => {
  return (
    <section id="about">
      <div className="container mx-auto flex px-10 py-20 md:flex-row flex-col items-center">
        {/* Your content goes here */}
        <h1 className="title-font sm:text-4xl text-3xl mb-4 font-medium text-black">
          Hello, I am Alex Asenov!
        </h1>
        <div className="ml-5">
          <span>
            I am Software enthusiast with Over 5 years of Personal experience
            and 2 Professional @ CommerzBank AG - Germany
          </span>
        </div>
        {/* Other elements */}
      </div>
    </section>
  );
};

export default page;
