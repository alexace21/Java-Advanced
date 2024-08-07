package cars.service.RapidApi.web;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import cars.service.RapidApi.model.dtos.CarDTO;
import cars.service.RapidApi.service.impl.ShopServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

@WebMvcTest(CarController.class)
@ExtendWith(SpringExtension.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShopServiceImpl shopService;

    @Autowired
    private ObjectMapper objectMapper;

    private CarDTO carDTO;

    @BeforeEach
    void setUp() {
        carDTO = new CarDTO();
        carDTO.setId(1L);
        carDTO.setMake("Tesla");
        carDTO.setModel("Model S");
        carDTO.setOwner("John Doe");
        carDTO.setPrice("60000");
    }

    @Test
    void createCarOffer_ShouldReturnCreatedCar() throws Exception {
        when(shopService.createCarOffer(any(CarDTO.class))).thenReturn(carDTO);

        mockMvc.perform(post("/shop/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.make").value("Tesla"))
                .andExpect(jsonPath("$.model").value("Model S"))
                .andExpect(jsonPath("$.owner").value("John Doe"))
                .andExpect(jsonPath("$.price").value("60000"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getAllCars_ShouldReturnListOfCars() throws Exception {
        when(shopService.fetchAllOffers()).thenReturn(List.of(carDTO));

        mockMvc.perform(get("/shop/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].make").value("Tesla"))
                .andExpect(jsonPath("$[0].model").value("Model S"))
                .andExpect(jsonPath("$[0].owner").value("John Doe"))
                .andExpect(jsonPath("$[0].price").value("60000"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteCarOffer_ShouldReturnDeletedCar() throws Exception {
        when(shopService.deleteCarOfferById("1")).thenReturn(carDTO);

        mockMvc.perform(delete("/shop/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.make").value("Tesla"))
                .andExpect(jsonPath("$.model").value("Model S"))
                .andExpect(jsonPath("$.owner").value("John Doe"))
                .andExpect(jsonPath("$.price").value("60000"))
                .andDo(MockMvcResultHandlers.print());
    }
}
