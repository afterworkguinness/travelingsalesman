package com.sandbox.travelingsalesman;

import com.sandbox.travelingsalesman.util.DistanceCalculator;
import com.sandbox.travelingsalesman.util.Pair;

import java.util.*;


public class Navigator implements INavigator {

    /*
    Lots of system.out.println's in here. Would have preferred to print these using a logging framework at debug level, but this is not allowed as per the requirements
     */

    private Set<Coordinate> planetsNotYetVisited;

    /**
     * Calculates an "optimal" path to vist a set of unique destinations given a starting point. The path is calculated using the Nearest Neighbour heuristic algorithm which has increased performance over an exact one.
     *
     * @param spaceshipPosition starting position
     * @param destinations      set of destinations to visit
     * @return "optimal" path to vist without returning to the origin destination.
     */
    public Coordinate[] route(Coordinate spaceshipPosition, Coordinate[] destinations) {
        final int NUMBER_OF_DESTINATIONS = destinations.length;
        planetsNotYetVisited = new HashSet<Coordinate>(Arrays.asList(destinations));

        if (spaceshipPosition == null) {
            throw new RuntimeException("Spaceship position is null!");
        }

        if (destinations == null) {
            throw new RuntimeException("Destinations is null!");
        }

        if (destinations.length == 0) {
            return new Coordinate[0];
        }
        System.out.println("Coordinates to visit: " + Arrays.toString(destinations) + " starting from " + spaceshipPosition);
        Coordinate[] optimalRoute = findOptimalRoute(spaceshipPosition, new Coordinate[NUMBER_OF_DESTINATIONS], 0);
        System.out.println("Optimal route: " + Arrays.toString(optimalRoute));
        return optimalRoute;
    }

    /*
        The problem statement said to design a function to plot an optimal route to visit a unique set of planets. Though the test case was made up of only 8 planets, the problem statement was not limited to such a small dataset. Given the problem statement didn't provide an indication of the posible data sizes, I went with a heuristic algorithim to calculate the path. The algorithim may not always produce the most optimal path, but will scale better performance wise as the data set grows compare to an exact solution which only perfoms OK on very small datasets.
     */
    private Coordinate[] findOptimalRoute(Coordinate currentPlanet, Coordinate[] optimalRoute, int counter) {

        if (planetsNotYetVisited.size() == 1) {
            Iterator<Coordinate> iterator = planetsNotYetVisited.iterator();
            Coordinate lastPlanetToVisit = iterator.next();
            iterator.remove();
            optimalRoute[counter] = lastPlanetToVisit;
            return optimalRoute;
        }
        Coordinate nearestNeighbour = findNearestNeighbour(currentPlanet);
        optimalRoute[counter] = nearestNeighbour;
        return findOptimalRoute(nearestNeighbour, optimalRoute, ++counter);

    }

    private Coordinate findNearestNeighbour(Coordinate currentPlanet) {

        planetsNotYetVisited.remove(currentPlanet);

        List<Pair<Coordinate, Double>> planetsAndTheirDistances = new ArrayList<Pair<Coordinate, Double>>();

        System.out.println("Finding nearest neighbour to " + currentPlanet + ">>>");

        for (Coordinate planetNotYetVisited : planetsNotYetVisited) {
            double distanceFromCurrentPlanet = DistanceCalculator.calcuateDistance(currentPlanet, planetNotYetVisited);
            Pair<Coordinate, Double> planetDistance = new Pair<Coordinate, Double>(planetNotYetVisited, distanceFromCurrentPlanet);
            planetsAndTheirDistances.add(planetDistance);
            System.out.println("Neighbour: " + planetDistance);
        }

        Collections.sort(planetsAndTheirDistances, PlanetDistanceComparator.getInstance());
        final int SMALLEST_DISTANCE_INDEX = 0;
        Pair<Coordinate, Double> nearestNeighbour = planetsAndTheirDistances.get(SMALLEST_DISTANCE_INDEX);

        Coordinate coordinateOfNearestNeighbour = nearestNeighbour.getFirst();
        Double distanceToNearestNeighbour = nearestNeighbour.getSecond();
        System.out.println("Found the nearest neighbour to " + currentPlanet + " -> " + coordinateOfNearestNeighbour + " with a distance of " + distanceToNearestNeighbour);
        planetsNotYetVisited.remove(coordinateOfNearestNeighbour);
        return coordinateOfNearestNeighbour;
    }
}