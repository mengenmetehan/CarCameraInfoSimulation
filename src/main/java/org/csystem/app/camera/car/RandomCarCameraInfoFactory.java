package org.csystem.app.camera.car;

import org.csystem.app.data.entity.CarCameraInfo;
import org.csystem.util.string.StringUtil;

import java.util.Random;

public class RandomCarCameraInfoFactory {

    private final Random random;

    private String generatePlate()
    {
        var num = random.nextInt(81) + 1;
        var text = StringUtil.getRandomTextEN(random, random.nextInt(3) + 1).toUpperCase();
        var value = random.nextInt ( 10000) + 1;

        return String.format("%02d %s %04d ", num, text, value);
    }

    public RandomCarCameraInfoFactory(Random random) {
        this.random = random;
    }

    public CarCameraInfo createCarCameraInfo()
    {
        return new CarCameraInfo(generatePlate(), random.nextInt( 180) + 40);
    }


}
