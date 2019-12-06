package src

import java.io.File

fun main() {

    val fileName = ClassLoader.getSystemResource("resources/inputDay2.txt").path

    val lines: List<String> = File(fileName).readLines()[0].split(",")

    val result : MutableList<Int> = ArrayList(lines.size)

    for(element in lines) {
        result.add(element.toInt())
    }

    var position = 0
    var noun = 0
    var verb = 0

        for(noun in 0..99) {
            for(verb in 0..99) {
                resetMemory(result, lines)
                position = 0
                result[1] = noun
                result[2] = verb

                while(position < result.size - 4  && result[0] != 19690720) {

                    when ( result[position]) {
                        99 ->  {
                            if(result[0] != 19690720) {
                                resetMemory(result, lines)
                                position = result.size
                            } else{
                                println(" result: ${result[0]}")
                                println("noun = $noun verb ≈ $verb")
                                return
                            }
                        }
                        1 -> {
                            result[ result[position + 3] ] = result[ result[position + 1] ] + result[ result[position + 2]]
                            position += 4
                        }
                        2 -> {
                            result[ result[position + 3] ] = result[result[position + 1]] * result[result[position + 2]]
                            position += 4
                        }
                    }
                }
                if(result[0] == 19690720){
                    println(" result: ${result[0]}")
                    println("noun = $noun verb ≈ $verb")
                }
        }
    }
    print("ended computation")
    println(" result: ${result[0]}")
    println("noun = $noun verb ≈ $verb")
}

private fun resetMemory(
    result: MutableList<Int>,
    lines: List<String>
) {
    result.clear()
    for (element in lines) {
        result.add(element.toInt())
    }
}