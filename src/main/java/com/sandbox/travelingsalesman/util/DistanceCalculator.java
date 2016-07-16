package com.sandbox.travelingsalesman.util;

import com.sandbox.travelingsalesman.Coordinate;

/**
 * Created by paul on 2016-07-10.
 */
public class DistanceCalculator {

    /**
     * @param startingPlanetCoordinate
     * @param destinationPlanetCoordinate
     * @return Distance between two coordinates using Pythagorean theorem
     */
    public static double calcuateDistance(Coordinate startingPlanetCoordinate, Coordinate destinationPlanetCoordinate) {

        if (startingPlanetCoordinate == null || destinationPlanetCoordinate == null) {
            throw new RuntimeException("One or both of starting and destination coordinates is null! startingPlanetCoordinate[" + startingPlanetCoordinate + "] destinationPlanetCoordinate[" + destinationPlanetCoordinate + "]");
        }

        if (startingPlanetCoordinate.equals(destinationPlanetCoordinate)) {
            return 0;
        }

        int distanceBetweenXcordinates = destinationPlanetCoordinate.x - startingPlanetCoordinate.x;
        double squaredDistanceBetweenXcoordinates = Math.pow(distanceBetweenXcordinates, 2);

        int distanceBetweenYcoordinates = destinationPlanetCoordinate.y - startingPlanetCoordinate.y;
        double squaredDistanceBetweenYcoordinates = Math.pow(distanceBetweenYcoordinates, 2);

        return Math.sqrt(squaredDistanceBetweenXcoordinates + squaredDistanceBetweenYcoordinates);
    }
}
