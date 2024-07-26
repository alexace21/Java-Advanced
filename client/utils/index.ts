export async function fetchCars() {
  const headers = {
    "Content-Type": "application/json",
  };

  const response = await fetch("http://localhost:8080/shop/all", {
    headers: headers,
    });

    const result = await response.json();

    return result;
}
