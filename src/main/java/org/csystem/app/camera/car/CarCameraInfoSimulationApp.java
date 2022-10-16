package org.csystem.app.camera.car;

import org.csystem.app.data.entity.CarCameraInfo;
import org.csystem.util.console.Console;
import org.csystem.util.console.commandline.CommandLineArgsUtil;
import org.csystem.util.numeric.NumberUtil;
import org.csystem.util.thread.ThreadUtil;

import java.util.*;

public class CarCameraInfoSimulationApp {

    private static void doSimulation(int count, HashMap<String, Set<CarCameraInfo>> carMap)
    {
        var factory = new RandomCarCameraInfoFactory(new Random());
        var random = new Random();

        for (int i = 0; i < count; i++)
        {
            Console.write(".");
            var ci = factory.createCarCameraInfo();

            if(!carMap.containsKey(ci.getPlate())) {
                var set = new TreeSet<CarCameraInfo>(Comparator.reverseOrder());

                set.add(ci);
                carMap.put(ci.getPlate(), set);
            }
            else
                carMap.get(ci.getPlate()).add(ci);

            ThreadUtil.sleep(500);
        }
    }

    private static void printResult(HashMap<String, Set<CarCameraInfo>> carMap)
    {
        Console.writeLine("Araç geçiş bilgileri");

        for (var p : carMap.keySet()) {
            Console.write("%s: ", p);
            for (var ci : carMap.get(p))
                Console.write("%s, ", ci);

            Console.writeLine();
        }
    }


    public static void run(String [] args)
    {
        CommandLineArgsUtil.checkLengthEquals(args, 1, "Wrong Number of  Arguments");

        var optInt = NumberUtil.toInt(args[0]);

        if (optInt.isEmpty()) {
            Console.Error.writeLine("Invalid count value");
            return;
        }
        var carMap = new HashMap<String, Set<CarCameraInfo>>();
        var count = optInt.getAsInt();
        doSimulation(count, carMap);
        printResult(carMap);


    }
}
