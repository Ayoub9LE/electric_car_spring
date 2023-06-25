package ma.ensias.app;

import ma.ensias.app.controller.CarController;
import ma.ensias.app.entity.Car;
import ma.ensias.app.enums.CarColor;
import ma.ensias.app.service.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class RentApplicationTests {
    
    @Mock
    private CarService carService;
    
    @InjectMocks
    private CarController carController;
    
    private MockMvc mockMvc;
    
    @Test
    public void testGetCars() throws Exception {
        Car car1 = new Car(1L, "Brand 1", CarColor.RED);
        Car car2 = new Car(2L, "Brand 2", CarColor.BLUE);
        List<Car> carList = Arrays.asList(car1, car2);
        
        when(carService.getCars()).thenReturn(carList);
        
        mockMvc.perform(get("/api/v1/cars"))
            .andExpect(status().isOk())
            .andExpect(content().json("[{\"id\":1,\"brand\":\"Brand 1\",\"color\":\"RED\"},{\"id\":2,\"brand\":\"Brand 2\",\"color\":\"BLUE\"}]"));
    }
    
    
    @Test
    public void testAddCar() throws Exception {
        Car car = new Car(1L, "Brand 1", CarColor.RED);
        
        mockMvc.perform(post("/api/v1/cars")
            .contentType("application/json")
            .content("{\"id\":1,\"brand\":\"Brand 1\",\"color\":\"RED\"}"))
            .andExpect(status().isOk());
        
        verify(carService, times(1)).addCar(car);
    }
    
    
    @Test
    public void testUpdateCar() throws Exception {
        Long carId = 1L;
        double pricePerDay = 100.0;
        CarColor color = CarColor.GREEN;
        
        mockMvc.perform(put("/api/v1/cars/{carId}", carId)
            .param("pricePerDay", String.valueOf(pricePerDay))
            .param("color", color.toString()))
            .andExpect(status().isOk());
        
        verify(carService, times(1)).updateCar(carId, pricePerDay, color);
    }
    
    
    @Test
    public void testDeleteCarById() throws Exception {
        Long carId = 1L;
        
        mockMvc.perform(delete("/api/v1/cars/{carId}", carId))
            .andExpect(status().isOk());
        
        verify(carService, times(1)).deleteCarById(carId);
    }
    
    
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
    }
    
}
