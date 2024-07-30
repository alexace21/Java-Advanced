import LoginForm from '@/components/LoginForm'
import React from 'react'

const page = () => {
  return (
    <main className="overflow-hidden">
      <div className="">
        <h1 className="text-center text-4xl font-extrabold pt-32">Login</h1>
        <LoginForm />
      </div>
    </main>
  )
}

export default page