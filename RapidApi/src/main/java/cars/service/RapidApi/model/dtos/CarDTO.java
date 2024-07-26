package cars.service.RapidApi.model.dtos;

public record CarDTO(
        Long id,
        Long city_mpg,
        Long combination_mpg,
        int cylinders,
        Double displacement,
        String drive,
        String fuel_type,
        Long highway_mpg,
        String make,
        String model,
        String transmission,
        Long year
) {
}
