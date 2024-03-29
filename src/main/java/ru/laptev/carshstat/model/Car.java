package ru.laptev.carshstat.model;
/*
 * Created by Laptev Egor 2/16/2021
 * */

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Car {

    @Id
    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "model")
    private String model;

}
