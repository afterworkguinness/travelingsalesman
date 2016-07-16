package com.sandbox.travelingsalesman;


import com.sandbox.travelingsalesman.util.DistanceCalculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DistanceCalculatorTest {

    @Test
    public void testCalcualteDistance() {

        Coordinate startPlanet = new Coordinate(1,2);
        Coordinate nextPlanet = new Coordinate(3,5);
        double actualDistance = DistanceCalculator.calcuateDistance(startPlanet, nextPlanet);
        double expectedDistance = 3.60555127546;
        assertEquals("(1,2)(3,5)",expectedDistance , actualDistance, 0.001);

        startPlanet = new Coordinate(-1,-2);
        nextPlanet = new Coordinate(3,5);
        actualDistance = DistanceCalculator.calcuateDistance(startPlanet, nextPlanet);
        expectedDistance = 8.06225774829855;
        assertEquals("(-1,-2)(3,5)", expectedDistance , actualDistance, 0.001);

        startPlanet = new Coordinate(-1,-2);
        nextPlanet = new Coordinate(-3,5);
        actualDistance = DistanceCalculator.calcuateDistance(startPlanet, nextPlanet);
        expectedDistance = 7.280109889;
        assertEquals("(-1,-2)(-3,5)", expectedDistance , actualDistance, 0.001);


        startPlanet = new Coordinate(1,2);
        nextPlanet = new Coordinate(1,2);
        actualDistance = DistanceCalculator.calcuateDistance(startPlanet, nextPlanet);
        expectedDistance = 0.0;
        assertEquals("(1,2)(1,2)", expectedDistance , actualDistance, 0.000);

    }
}
