package src

import java.io.File


fun main() {

    val fileName = ClassLoader.getSystemResource("resources/inputDay1.txt").path

    val lines: List<String> = File(fileName).readLines()

    var sumOfFuelRequirements: Int = 0
    lines.forEach { starMass ->
        var massToGetFuelFor: Int = starMass.toInt()
        var fuelRequiredForCurrentStar = massToGetFuelFor

        while(fuelRequiredForCurrentStar > 0) {
            fuelRequiredForCurrentStar = massToGetFuelFor / 3 - 2
            if(fuelRequiredForCurrentStar > 0) {
                sumOfFuelRequirements += fuelRequiredForCurrentStar
                massToGetFuelFor = fuelRequiredForCurrentStar
            }
        }
    }
    print(sumOfFuelRequirements)
}
