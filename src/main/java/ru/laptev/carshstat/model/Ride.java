package ru.laptev.carshstat.model;
/*
 * Created by Laptev Egor 2/5/2021
 * */

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ride")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Ride {

    @Id
    @Column(name = "ride_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rideId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "ride_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime rideDate;

    @Column(name = "ride_cost")
    private double rideCost;

    @Column(name = "company_name")
    @Enumerated(EnumType.STRING)
    private Company company;


}
