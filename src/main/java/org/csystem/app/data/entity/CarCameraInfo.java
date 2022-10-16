package org.csystem.app.data.entity;

import org.csystem.util.datetime.DateTimeFormatterUtil;

import java.time.LocalDateTime;


public class CarCameraInfo implements Comparable<CarCameraInfo>{

    private final String plate;
    private final int speed;
    private final LocalDateTime dateTime;

    public CarCameraInfo(String plate, int speed) {
        this.plate = plate;
        this.dateTime = LocalDateTime.now();
        this.speed = speed;
    }

    public String getPlate() {
        return plate;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public String toString() {

        var formatter = DateTimeFormatterUtil.DATETIME_DOT_SEC_TR;

        return String.format("[%s] (%d km / h) %s", plate, speed, formatter.format(dateTime));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CarCameraInfo))
            return false;

        var c = (CarCameraInfo) other;

        return c.plate.equals(plate) && c.dateTime.equals(dateTime);

    }

    @Override
    public int compareTo(CarCameraInfo other) {
        if (dateTime.isBefore(other.dateTime))
            return -1;

        if (dateTime.isAfter(other.dateTime))
            return 1;

        return 0;
    }


}

