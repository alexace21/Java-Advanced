"use client";

import Link from 'next/link';
import Image from 'next/image';

import CustomButton from './CustomButton';
import { useRouter } from 'next/navigation';

const Navbar = () => {
    const router = useRouter();

    // const handleSignIn = (event) => {
    //     event.preventDefault();
    //     router.push("/users");

    // }

  return (
    <header className='w-full absolute z-10'>
        <nav className='max-w-[1440px] mx-auto flex justify-between items-center sm:px-16 px-6 py-4'>
            <Link href="/" className='flex justify-center items-center'>
                <Image 
                    src="/logo.svg"
                    alt='72cars Logo'
                    width={118}
                    height={18}
                    className="object-contain"
                />
            </Link>

            {/* <CustomButton 
                title="Sign In"
                btnType="button"
                containerStyles='text-primary-blue rounded-full bg-white min-w-[130px]'
                handleClick={(e) => handleSignIn(e)}
            /> */}

            <Link href="/users" prefetch={false}>
                <CustomButton 
                title="Sign In"
                btnType="button"
                containerStyles='text-primary-blue rounded-full bg-white min-w-[130px]'
            />
            </Link>
        </nav>
    </header>
  )
}

export default Navbar