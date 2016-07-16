package com.sandbox.travelingsalesman;

public interface INavigator {
    Coordinate[] route(Coordinate spaceshipPosition, Coordinate[] destinations);
}
