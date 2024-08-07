package softuni.defense.project.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import softuni.defense.project.model.dtos.CarDTO;
import softuni.defense.project.service.impl.CarServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CatalogControllerIT {

    @Autowired
    private MockMvc mockedMvc;
    @MockBean
    private CarServiceImpl carService;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void testGetSearchedCar() throws Exception {
        List<CarDTO> expectedResult = List.of(new CarDTO(
                Long.valueOf(1), Long.valueOf(9), Long.valueOf(8), 6, 4.5, "RWD", "Gas", Long.valueOf(7), "Toyota", "Corolla", "Automatic", Long.valueOf(2012), "3000", "aleks.asenov2@outlook.com"
        ));

        Map<String, String> parameters = new HashMap<>();

        parameters.put("limit", "10");
        parameters.put("year", "2022");

        Mockito.when(carService.getCarsByQueryParameters(parameters)).thenReturn(expectedResult);

        mockedMvc.perform(get("http://localhost:8080/shop")
                        .param("limit", "10")
                        .param("year", "2022")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.ALL)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}
