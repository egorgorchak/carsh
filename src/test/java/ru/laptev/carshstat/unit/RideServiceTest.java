package ru.laptev.carshstat.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.laptev.carshstat.model.Car;
import ru.laptev.carshstat.model.Ride;
import ru.laptev.carshstat.repository.RidesRepository;
import ru.laptev.carshstat.sevice.RideService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class RideServiceTest {

    @Autowired
    private RideService rideService;

    @MockBean
    private RidesRepository ridesRepository;

    private List<Ride> rides;

    @BeforeEach
    void setUp() {
        Car car1 = new Car(1L, "SampleModule1", "a111aa111");
        Car car2 = new Car(2L, "SampleModule2", "b222bb222");
        Ride ride1 = new Ride(1L, car1, LocalDateTime.of(2020, 1, 1, 10, 0), 10.10d);
        Ride ride2 = new Ride(2L, car2, LocalDateTime.of(2021, 2, 2, 20, 0), 20.2d);
        rides = new ArrayList<>();
        rides.add(ride1);
        rides.add(ride2);
    }

    @Test
    void getAllRides_ReturnAllRides() {
        when(ridesRepository.findAll()).thenReturn(rides);
        List<Ride> allRides = rideService.getAllRides();

        assertEquals(allRides, rides);
    }

    @Test
    void getRideById_ValidId_SuccessfulReturn() {
        Ride expectedRide = rides.get(1);
        when(ridesRepository.findById(1L)).thenReturn(Optional.of(expectedRide));
        Ride rideById = rideService.getRideById(1L);

        assertEquals(rideById, expectedRide);
    }

    @Test
    void getRideById_InvalidId_ThrowsException() {
        when(ridesRepository.findById(100L)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> rideService.getRideById(1L));
    }


    @Test
    void updateRide() {
    }
}