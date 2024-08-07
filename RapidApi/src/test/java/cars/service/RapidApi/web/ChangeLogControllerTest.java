package cars.service.RapidApi.web;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import cars.service.RapidApi.model.dtos.CarLogDTO;
import cars.service.RapidApi.service.ChangeLogService;
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

import java.time.LocalDate;
import java.util.List;

@WebMvcTest(ChangeLogController.class)
@ExtendWith(SpringExtension.class)
public class ChangeLogControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChangeLogService changeLogService;

    private CarLogDTO carLogDTO;

    @BeforeEach
    void setUp() {
        carLogDTO = new CarLogDTO(
                "1",
                "Tesla",
                "Model S",
                "John Doe",
                "60000",
                LocalDate.now().toString(),
                "AVAILABLE"
        );
    }

    @Test
    void getAllCars_ShouldReturnListOfCarLogs() throws Exception {
        when(changeLogService.getAllCarLogs()).thenReturn(List.of(carLogDTO));

        mockMvc.perform(get("/change-log/cars")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].make").value("Tesla"))
                .andExpect(jsonPath("$[0].model").value("Model S"))
                .andExpect(jsonPath("$[0].userOwner").value("John Doe"))
                .andExpect(jsonPath("$[0].price").value("60000"))
                .andExpect(jsonPath("$[0].status").value("AVAILABLE"))
                .andDo(MockMvcResultHandlers.print());
    }
}
