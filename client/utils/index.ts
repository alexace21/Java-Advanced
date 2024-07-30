import { CarCardProps, FilterProps } from "@/types";

export async function fetchCars(filters: FilterProps) {
  const { manufacturer, year, model, limit, fuel } = filters;

  const headers = {
    "Content-Type": "application/json",
    'Access-Control-Allow-Origin': 'http://localhost:3000'
  };
  let url;

  if (year || model || limit || fuel || manufacturer) {
    url = new URL("http://localhost:8080/shop?");

    if (manufacturer) {
      url.searchParams.append("make", manufacturer);
    }

    if (year) {
      url.searchParams.append("year", `${year}`);
    }

    if (model) {
      url.searchParams.append("model", model);
    }

    if (limit) {
      url.searchParams.append("limit", `${limit}`);
    }

    if (fuel) {
      url.searchParams.append("fuel", fuel);
    }
  } else {
    url = new URL("http://localhost:8080/shop/all");
  }
  

  console.log("ops ", url);
  
  const response = await fetch(url, {
    headers: headers,
  });

  const result = await response.json();

  return result;
}

export const calculateCarRent = (city_mpg: number, year: number) => {
  const basePricePerDay = 50; // Base rental price per day in dollars
  const mileageFactor = 0.1; // Additional rate per mile driven
  const ageFactor = 0.05; // Additional rate per year of vehicle age

  // Calculate additional rate based on mileage and age
  const mileageRate = city_mpg * mileageFactor;
  const ageRate = (new Date().getFullYear() - year) * ageFactor;

  // Calculate total rental rate per day
  const rentalRatePerDay = basePricePerDay + mileageRate + ageRate;

  return rentalRatePerDay.toFixed(0);
};

export const generateCarImageUrl = (car: CarCardProps, angle?: string) => {
  // key... hrjavascript-mastery
  const url = new URL("https://cdn.imagin.studio/getimage");

  const { make, year, model } = car;

  url.searchParams.append("customer", "hrjavascript-mastery");

  url.searchParams.append("make", make);
  url.searchParams.append("modelFamily", model.split(" ")[0]);
  url.searchParams.append("zoomType", "fullscreen");
  url.searchParams.append("modelYear", `${year}`);
  url.searchParams.append("angle", `${angle}`);

  return `${url}`;
};

export const updateSearchParams = (type: string, value: string) => {
  // Get the current URL search params
  const searchParams = new URLSearchParams(window.location.search);

  // Set the specified search parameter to the given value
  searchParams.set(type, value);

  // Set the specified search parameter to the given value
  const newPathname = `${window.location.pathname}?${searchParams.toString()}`;

  return newPathname;
};

export const registerUser = async (email: string, password: string) => {
  let url = new URL("http://localhost:8080/users/register");

  const headers = {
    "Content-Type": "application/json",
    'Access-Control-Allow-Origin': 'http://localhost:3000'
  };

  const response = await fetch(url, {
    method: 'POST',
    headers: headers,
    body: JSON.stringify({ email, password })
  });

  if (response.ok) {
    // const result = await response.json();
    return "registered";
  } else {
    console.log("Error registering user!");
  }


}