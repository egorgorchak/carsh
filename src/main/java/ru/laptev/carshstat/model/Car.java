package ru.laptev.carshstat.model;
/*
 * Created by Laptev Egor 2/16/2021
 * */

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "model")
    @Enumerated(EnumType.STRING)
    private Model model;

    @Column(name = "plate_number")
    private String plateNumber;

    public Long getId() {
        return id;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    private enum Model {
        NISSAN_QASHQAI("NISSAN_QASHQAI"),
        VOLKSWAGEN_POLO("VOLKSWAGEN_POLO"),
        RENAULT_KAPTUR("RENAULT_KAPTUR");

        private String model;

        Model(String model) {
            this.model = model;
        }
    }
}
