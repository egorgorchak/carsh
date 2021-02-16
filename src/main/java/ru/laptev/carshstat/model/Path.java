package ru.laptev.carshstat.model;
/*
 * Created by Laptev Egor 2/16/2021
 * */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "path")
public class Path {

    @Id
    @Column(name = "path_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pathId;

    @Column(name = "start_lat")
    private double startLat;

    @Column(name = "start_lon")
    private double startLon;

    @Column(name = "finish_lat")
    private double finishLat;

    @Column(name = "finish_lon")
    private double finishLon;

    public Long getPathId() {
        return pathId;
    }

    public double getStartLat() {
        return startLat;
    }

    public void setStartLat(double startLat) {
        this.startLat = startLat;
    }

    public double getStartLon() {
        return startLon;
    }

    public void setStartLon(double startLon) {
        this.startLon = startLon;
    }

    public double getFinishLat() {
        return finishLat;
    }

    public void setFinishLat(double finishLat) {
        this.finishLat = finishLat;
    }

    public double getFinishLon() {
        return finishLon;
    }

    public void setFinishLon(double finishLon) {
        this.finishLon = finishLon;
    }
}
