package com.sandbox.travelingsalesman;

import com.sandbox.travelingsalesman.util.Pair;

import java.util.Comparator;

/**
 * Created by paul on 2016-07-10.
 */

    /*
    Ideally would have made PlanetDistanceComparator a Spring managed bean,
    but the requirements disallow libraries not already included in the pom
     */

public class PlanetDistanceComparator implements Comparator<Pair<Coordinate, Double>> {

    private static PlanetDistanceComparator instance = null;


    public static PlanetDistanceComparator getInstance() {
        if (instance == null) {
            instance = new PlanetDistanceComparator();
        }
        return instance;
    }

    /**
     * Compares based on distance between 2 planets
     *
     * @param planetA
     * @param planetB
     * @return -1 if planetA's distance from the current planet is shorter than planetB's,
     *          0 if planetA's distance from the current planet is equal to that of planetB's,
     *          1 if planetA's distance from the current planet is greater than planetB's
     */
    public int compare(Pair<Coordinate, Double> planetA, Pair<Coordinate, Double> planetB) {

        Double distanceToPlanetA = planetA.getSecond();
        Double distanceToPlanetB = planetB.getSecond();
        return distanceToPlanetA.compareTo(distanceToPlanetB);
    }
}
