import { CarCardProps, FilterProps } from "@/types";

export async function fetchCars(filters: FilterProps) {
  const { manufacturer, year, model, limit, fuel } = filters;

  const headers = {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "http://localhost:3000",
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

  if (response.status === 401) {
    return 500
  }

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

export const registerUser = async (email: string, password: string, firstName: string, lastName: string) => {
  let url = new URL("http://localhost:8080/users/register");

  const headers = {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "http://localhost:3000",
  };

  const response = await fetch(url, {
    method: "POST",
    headers: headers,
    body: JSON.stringify({ email, password, firstName, lastName }),
  });

  if (response.ok) {
    return 200;
  } else {
    console.log("Error registering user!");
    const body = await response.json();
    return body.message;
  }
};

export const loginUser = async (email: string, password: string) => {
  let url = new URL(`http://localhost:8080/users/login`);

  const headers = {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "http://localhost:3000",
  };

  const response = await fetch(url, {
    method: "POST",
    headers: headers,
    body: JSON.stringify({ email, password }),
  });

  const body = await response.json();

  console.log(body)

  console.log(response.ok);
  
  if (response.ok) {
    setAuthToken(body?.token);
    return 200;
  } else {
    console.log("Error Signing IN!");
    return body.message;
  }
};

export const createCarOffer = async (
            city_mpg: string, 
            combination_mpg: string,
            cylinders: string,
            displacement: string,
            drive: string,
            fuel_type: string,
            highway_mpg: string,
            make: string,
            model: string,
            transmission: string,
            year: string,
            price: string
) => {
  let url = new URL(`http://localhost:8080/cars/create`);

  const owner = window.localStorage.getItem("auth_user");

  const headers = {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "http://localhost:3000",
    "Authorization": "Bearer " + getAuthToken()
  };
  
  const response = await fetch(url, {
    method: "POST",
    headers: headers,
    body: JSON.stringify({ 
            city_mpg, 
            combination_mpg,
            cylinders,
            displacement,
            drive,
            fuel_type,
            highway_mpg,
            make,
            model,
            transmission,
            year,
            price,
            owner
    }),
  });

  const body = await response.json();

  console.log(body)

  console.log(response.ok);
  
  if (response.status === 403) {
    return 403;
  }


  if (response.ok) {
    return body;
  } else {
    console.log("Error Creating offer!");
    return body.message? body.message : body;
  }
}

export const submitFeedbackForm = async (
        inputFirstName: string,
        selectedOption: string, 
        reasonDescription: string, 
        adviceDescription: string, 
        qualityServiceRate: number, 
        timelinessRate: number, 
        customerServiceRate: number, 
        priceRate: number, 
        cleanlinessRate: number, 
        recommendOption: string,
        ownerUser: string | null
) => {

  let url = new URL(`http://localhost:8080/feedback`);

  const headers = {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "http://localhost:3000",
    "Authorization": "Bearer " + getAuthToken()
  };

  const response = await fetch(url, {
    method: "POST",
    headers: headers,
    body: JSON.stringify({ 
            inputFirstName, 
            selectedOption,
            reasonDescription,
            adviceDescription,
            qualityServiceRate,
            timelinessRate,
            customerServiceRate,
            priceRate,
            cleanlinessRate,
            recommendOption,
            ownerUser
    }),
  });

  // const body = await response.json();
  console.log(response)

  if (response.status === 403) {
    return 403;
  }
  
  if (response.ok) {
    return 200;
  } else {
    console.log("Error Submitting Feedback!");
    return 401;
  }
}

export const deleteCarForSale = async (id: number) => {
  let url = new URL(`http://localhost:8080/cars/${id}`);
  const currentLogin = window.localStorage.getItem("auth_user");

  const headers = {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "http://localhost:3000",
    "Authorization": "Bearer " + getAuthToken()
  };

  const response = await fetch(url, {
    method: "DELETE",
    headers: headers,
    body: currentLogin
  });

  if (response.status === 403) {
    return 403;
  }

  if (response.ok) {
    return 200;
  } else {
    return 400;
  }
  
}

export const fetchCarsForSale = async () => {
  let url = new URL(`http://localhost:8080/cars`);

  const headers = {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "http://localhost:3000",
    "Authorization": "Bearer " + getAuthToken()
  };

  const response = await fetch(url, {
    method: "GET",
    headers: headers
  });

  const body = await response.json();

  console.log(body);

  console.log(response.ok);

  if (response.status === 403) {
    return 403;
  }
  
  if (response.ok) {
    return body;
  } else {
    console.log("Error fetching Cars for sale!");
    return body.message? body.message : body;
  }
}

export const fetchAllFeedbackChangeLog = async () => {
  let url = new URL(`http://localhost:8080/change-log/feedback`);

  const headers = {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "http://localhost:3000",
    "Authorization": "Bearer " + getAuthToken()
  };

  const response = await fetch(url, {
    method: "GET",
    headers: headers
  });

  const body = await response.json();

  console.log(body);

  console.log(response.ok);

  if (response.status === 403) {
    return 403;
  }
  
  if (response.ok) {
    return body;
  } else {
    console.log("Error fetching Car Change logs!");
    return body.message? body.message : body;
  }
}

export const fetchAllRegisteredUsers = async () => {
  let url = new URL(`http://localhost:8080/change-log/users`);

  const headers = {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "http://localhost:3000",
    "Authorization": "Bearer " + getAuthToken()
  };

  const response = await fetch(url, {
    method: "GET",
    headers: headers
  });

  const body = await response.json();

  console.log(body);

  console.log(response.ok);

  if (response.status === 403) {
    return 403;
  }
  
  if (response.ok) {
    return body;
  } else {
    console.log("Error fetching User Change logs!");
    return body.message? body.message : body;
  }
}

export const fetchAllRegisteredCars = async () => {
  let url = new URL(`http://localhost:8080/change-log/cars`);

  const headers = {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "http://localhost:3000",
    "Authorization": "Bearer " + getAuthToken()
  };

  const response = await fetch(url, {
    method: "GET",
    headers: headers
  });

  const body = await response.json();

  console.log(body);

  console.log(response.ok);

  if (response.status === 403) {
    return 403;
  }

  if (response.ok) {
    return body;
  } else {
    console.log("Error fetching Car Change logs!");
    return body.message? body.message : body;
  }
}

export const fetchAllCryptoData = async () => {
  let url = new URL(`http://localhost:8080/crypto`);

  const headers = {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "http://localhost:3000",
    "Authorization": "Bearer " + getAuthToken()
  };

  const response = await fetch(url, {
    method: "GET",
    headers: headers
  });

  const body = await response.json();

  console.log(body);

  console.log(response.ok);

  if (response.status === 403) {
    return 403;
  }
  
  if (response.ok) {
    return body;
  } else {
    console.log("Error fetching Crypto data!");
    return body.message? body.message : body;
  }
}

export const resolveFeedback = async (id: string) => {
  let url = new URL(`http://localhost:8080/feedback/${id}`);

  const headers = {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "http://localhost:3000",
    "Authorization": "Bearer " + getAuthToken()
  };

  const response = await fetch(url, {
    method: "POST",
    headers: headers,
    body: JSON.stringify({id})
  });

  const body = await response.json();

  console.log(body);

  console.log(response.ok);

  if (response.status === 403) {
    return 403;
  }
  
  if (response.ok) {
    return body;
  } else {
    console.log("Error Resolving Feedback with Id: " + id);
    return body.message? body.message : body;
  }
};

export const removeUserFeedback = async (id: string) => {
  let url = new URL(`http://localhost:8080/feedback/${id}`);

  const headers = {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "http://localhost:3000",
    "Authorization": "Bearer " + getAuthToken()
  };

  const response = await fetch(url, {
    method: "DELETE",
    headers: headers,
    body: JSON.stringify({id})
  });

  const body = await response.json();

  console.log(body);

  console.log(response.ok);

  if (response.status === 403) {
    return 403;
  }
  
  if (response.ok) {
    return body;
  } else {
    console.log("Error Deleting Feedback with Id: " + id);
    return body.message? body.message : body;
  }
};

export const getAuthToken = () => {
  return window.localStorage.getItem("auth_token");
};

export const setAuthToken = (token: string) => {
  window.localStorage.setItem("auth_token", token);
};

export const getAuthUser = () => {
  return window.localStorage.getItem("auth_user");
}

