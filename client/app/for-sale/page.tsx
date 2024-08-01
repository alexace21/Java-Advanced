import SaleCatalog from '@/components/SaleCatalog'
import React from 'react'

const page = () => {
  return (
    <main className="overflow-hidden">
      <div className="">
        <h1 className="text-center text-4xl font-extrabold pt-32">Catalog Cars for SALE</h1>
        <SaleCatalog />
      </div>
    </main>
  )
}

export default page