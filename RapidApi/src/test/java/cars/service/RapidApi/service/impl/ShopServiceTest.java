package cars.service.RapidApi.service.impl;

import cars.service.RapidApi.model.dtos.CarDTO;
import cars.service.RapidApi.model.entities.CarEntity;
import cars.service.RapidApi.repository.CarRepository;
import cars.service.RapidApi.service.ChangeLogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShopServiceTest {
    @Mock
    private CarRepository carRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ChangeLogService changeLogService;

    @InjectMocks
    private ShopServiceImpl shopService;

    private CarDTO carDTO;
    private CarEntity carEntity;

    @BeforeEach
    void setUp() {
        carDTO = new CarDTO();
        carDTO.setMake("Tesla");
        carDTO.setModel("Model S");
        carDTO.setOwner("John Doe");
        carDTO.setPrice("60000");


        carEntity = new CarEntity();
        carEntity.setMake("Tesla");
        carEntity.setModel("Model S");
        carEntity.setOwner("John Doe");
        carEntity.setPrice("60000");
    }

    @Test
    void createCarOffer_ShouldPersistCarAndReturnDTO() {
        when(modelMapper.map(carDTO, CarEntity.class)).thenReturn(carEntity);
        when(carRepository.save(carEntity)).thenReturn(carEntity);
        when(modelMapper.map(carEntity, CarDTO.class)).thenReturn(carDTO);

        CarDTO result = shopService.createCarOffer(carDTO);

        assertNotNull(result);
        assertEquals("Tesla", result.getMake());

        verify(carRepository).save(carEntity);
        verify(changeLogService).createCarOfferChangeLog(carEntity);
    }

    @Test
    void fetchAllOffers_ShouldReturnListOfCarDTOs() {
        when(carRepository.findAll()).thenReturn(List.of(carEntity));
        when(modelMapper.map(carEntity, CarDTO.class)).thenReturn(carDTO);

        List<CarDTO> result = shopService.fetchAllOffers();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Tesla", result.get(0).getMake());

        verify(carRepository).findAll();
    }

    @Test
    void deleteCarOfferById_ShouldRemoveCarAndReturnDTO() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(carEntity));
        when(modelMapper.map(carEntity, CarDTO.class)).thenReturn(carDTO);

        CarDTO result = shopService.deleteCarOfferById("1");

        assertNotNull(result);
        assertEquals("Tesla", result.getMake());

        verify(changeLogService).trackRemovalOfCar(carEntity);
        verify(carRepository).delete(carEntity);
    }

    @Test
    void deleteCarOfferById_ShouldReturnNullWhenCarNotFound() {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        CarDTO result = shopService.deleteCarOfferById("1");

        assertNull(result);

        verify(changeLogService, never()).trackRemovalOfCar(any());
        verify(carRepository, never()).delete(any());
    }

    @Test
    void hasInitializedCarShop_ShouldReturnTrueWhenCountIsGreaterThanZero() {
        when(carRepository.count()).thenReturn(5L);

        boolean result = shopService.hasInitializedCarShop();

        assertTrue(result);
    }

    @Test
    void hasInitializedCarShop_ShouldReturnFalseWhenCountIsZero() {
        when(carRepository.count()).thenReturn(0L);

        boolean result = shopService.hasInitializedCarShop();

        assertFalse(result);
    }
}
