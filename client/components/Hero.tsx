"use client";

import Image from 'next/image';
import CustomButton from './CustomButton';
import { useAuthContext } from '@/context/AuthContext';
import { useEffect, useState } from 'react';

const Hero = () => {

  const { internationalization } = useAuthContext();

  const [headlineTitle, setHeadlineTitle] = useState("Find, book, or rent a car QUICKLY and EASILY with 72cars!");
  const [subHeadTitle, setSubHeadTitle] = useState("Streamline your car rental experience with our effortless booking process.")
  const [exloreButtonTitle, setExloreButtonTitle] = useState("Explore cars");

    const handleScroll = () => {
        const nextSection = document.getElementById("discover");
    
        if (nextSection) {
          nextSection.scrollIntoView({ behavior: "smooth" });
        }
      };


      useEffect(() => {
        console.log("Internationalization switched! " + internationalization);
    
        if (internationalization === "Български") {
            setHeadlineTitle("Намерете, резервирайте, или наемете под наем автомобил БЪРЗО и ЛЕСНО с нас 72cars!");
            setSubHeadTitle("Оптимизирайте своете преживяване при наем на автомобил с нашия безпроблемен процес на резервация!");
            setExloreButtonTitle("Разгледайте автомобили");
        } else {
            setHeadlineTitle("Find, book, or rent a car QUICKLY and EASILY with 72cars!");
            setSubHeadTitle("Streamline your car rental experience with our effortless booking process.");
            setExloreButtonTitle("Explore cars");
        }
        
      }, [internationalization])

  return (
    <div className='hero'>
        <div className='flex-1 pt-36 padding-x'>
            <h1 className='hero__title'>
                {headlineTitle}
            </h1>

            <p className='hero__subtitle'>
                {subHeadTitle}
            </p>

            <CustomButton 
                title={exloreButtonTitle}
                containerStyles="bg-primary-blue
                text-white rounded-full mt-10"
                handleClick={handleScroll}
            />
        </div>
        <div className='hero__image-container'>
            <div className='hero__image'>
                <Image src="/hero.png" alt='hero'
                fill className='object-contain'
                />
            </div>

                <div className='hero__image-overlay' />
        </div>
    </div>
  )
}

export default Hero