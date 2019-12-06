package src

import java.io.File
import kotlin.math.absoluteValue


fun main() {

    val fileName = ClassLoader.getSystemResource("resources/inputDay3.txt").path

    val firstWireInput: List<String> = File(fileName).readLines()[0].split(",")
    val secondWireInput: List<String> = File(fileName).readLines()[1].split(",")


    var markedCoordinatesByFirstWire = ArrayList<Pair<Int, Int>>()
    var markedCoordinatesBySecondWire = ArrayList<Pair<Int, Int>>()

    populateWirePass(firstWireInput, markedCoordinatesByFirstWire)
    populateWirePass(secondWireInput, markedCoordinatesBySecondWire)


    var crossedPoints = HashSet<Pair<Int, Int>>()
    for(value in markedCoordinatesByFirstWire) {
        if(markedCoordinatesBySecondWire.contains(value)){
            crossedPoints.add(value)
        }
    }
    for(value in markedCoordinatesBySecondWire) {
        if(markedCoordinatesByFirstWire.contains(value)){
            crossedPoints.add(value)
        }
    }

   // crossedPoints.remove(Pair(0,0))
    println("Found crossedValue total ${crossedPoints.size}")
    var manhatanDistance = crossedPoints.elementAt(1).copy()
    var minNumberOfSteps = Int.MAX_VALUE
    for(cross in crossedPoints) {

        var stepsToCrossByFirst = markedCoordinatesByFirstWire.indexOf(cross)
        var stepsToCrossBySecond = markedCoordinatesBySecondWire.indexOf(cross)
        var sumOrSteps = stepsToCrossByFirst + stepsToCrossBySecond
        if(sumOrSteps in 1 until minNumberOfSteps)
            minNumberOfSteps = sumOrSteps

        println("sum of steps is ${stepsToCrossByFirst + stepsToCrossBySecond}")
        var sumOfCoordinates = cross.first.absoluteValue + cross.second.absoluteValue
        if( sumOfCoordinates < (manhatanDistance.first.absoluteValue + manhatanDistance.second.absoluteValue)  && sumOfCoordinates > 0)
            manhatanDistance = cross.copy()
    }


    println(manhatanDistance)
    println("result of first part is ${manhatanDistance.first.absoluteValue + manhatanDistance.second.absoluteValue}")
    println(minNumberOfSteps)
}

private fun populateWirePass(
    firstWireInput: List<String>,
    markedCoordinatesByFirstWire: ArrayList<Pair<Int, Int>>
) {
    var currentCoordinate = Pair(0, 0)
    var newCoordinate = Pair(0, 0)
    markedCoordinatesByFirstWire.add(currentCoordinate)
    for (nextStep in firstWireInput) {
        when (nextStep[0]) {
            'R' -> {
                newCoordinate =
                    currentCoordinate.copy(first = currentCoordinate.first + nextStep.removeRange(0, 1).toInt())

            }
            'U' -> {
                newCoordinate =
                    currentCoordinate.copy(second = currentCoordinate.second + nextStep.removeRange(0, 1).toInt())
            }
            'L' -> {
                newCoordinate =
                    currentCoordinate.copy(first = currentCoordinate.first - nextStep.removeRange(0, 1).toInt())
            }
            'D' -> {
                newCoordinate =
                    currentCoordinate.copy(second = currentCoordinate.second - nextStep.removeRange(0, 1).toInt())
            }
        }
        when {
            newCoordinate.first > currentCoordinate.first  -> {

                    var differenceBetweenValues = (newCoordinate.first - currentCoordinate.first).absoluteValue
                    for(i in 1..differenceBetweenValues) {
                        markedCoordinatesByFirstWire.add(currentCoordinate.copy(first = currentCoordinate.first + i))
                    }
               // }
            }
            newCoordinate.first == currentCoordinate.first -> { }
            else -> {
                var differenceBetweenValues = (newCoordinate.first - currentCoordinate.first).absoluteValue
                for(i in 1..differenceBetweenValues) {
                    markedCoordinatesByFirstWire.add(currentCoordinate.copy(first = currentCoordinate.first - i))
                }
            }
        }
        when {
            newCoordinate.second > currentCoordinate.second   -> {
                var differenceBetweenValues = (newCoordinate.second - currentCoordinate.second).absoluteValue
                for(i in 1..differenceBetweenValues) {
                    markedCoordinatesByFirstWire.add(currentCoordinate.copy(second = currentCoordinate.second + i))
                }
            }
            newCoordinate.second == currentCoordinate.second -> {}
            else -> {
                var differenceBetweenValues = (currentCoordinate.second - newCoordinate.second).absoluteValue
                for(i in 1..differenceBetweenValues) {
                    markedCoordinatesByFirstWire.add(currentCoordinate.copy(second = currentCoordinate.second - i))
                }
            }
        }

        currentCoordinate = newCoordinate.copy()
    }
}

