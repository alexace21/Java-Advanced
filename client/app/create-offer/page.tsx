import OfferForm from '@/components/OfferForm'
import React from 'react'

const page = () => {
  return (
    <main className="overflow-hidden">
      <div className="">
        <h1 className="text-center text-4xl font-extrabold pt-32">Create Offer</h1>
        <OfferForm />
      </div>
    </main>
  )
}

export default page