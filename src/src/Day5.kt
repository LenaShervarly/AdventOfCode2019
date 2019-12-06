package src

import java.io.File

fun main() {

    val fileName = ClassLoader.getSystemResource("resources/test.txt").path

    val lines: List<String> = File(fileName).readLines()[0].split(",")

    val result : MutableList<Int> = ArrayList(lines.size)

    for(element in lines) {
        result.add(element.toInt())
    }

    var position = 0
    while(position < result.size - 2) {

        when {
            result[position] == 99 -> {
                println(" result: ${result[0]}")
                return
            }
            result[position] % 100 == 1 -> {
                var charArray = result[position].toString().trim().split("")
                var digits = ArrayList<Int>()

                for(i in 1 until charArray.size -1){
                    digits.add(charArray[i].toInt())
                }

                if(digits.last() == 1) {
                    if(digits.size == 2) {
                        result[ result[position + 3] ] = result[ result[position + 1] ] + result[ result[position + 2]]
                    } else if(digits.size == 3) {
                        result[ result[position + 3] ] = result[ position + 1] + result[ result[position + 2]]

                    } else if(digits.size == 4) {
                        if (digits[0] == 1 && digits[1] == 1) {
                            result[ result[position + 3] ] = result[ position + 1]  + result[position + 2]
                        } else { // digits[0] == 1 && digits1] == 0
                            result[ result[position + 3] ] = result[ result[position + 1] ] + result[position + 2]
                        }
                    }

                } else if(digits.last() == 2) {
                    if(digits.size == 2) {
                        result[ result[position + 3] ] = result[ result[position + 1] ] * result[ result[position + 2]]
                    } else if(digits.size == 3) {
                        result[ result[position + 3] ] = result[ position + 1] * result[ result[position + 2]]

                    } else if(digits.size == 4) {
                        if (digits[0] == 1 && digits[1] == 1) {
                            result[ result[position + 3] ] = result[ position + 1]  * result[position + 2]
                        } else { // digits[0] == 1 && digits1] == 0
                            result[ result[position + 3] ] = result[ result[position + 1] ] * result[position + 2]

                        }
                    }
                }
                position += 4
            }
            result[position] == 1 -> {
                result[ result[position + 3] ] = result[ result[position + 1] ] + result[ result[position + 2]]
                position += 4
            }
            result[position] == 2 -> {
                result[ result[position + 3] ] = result[result[position + 1]] * result[result[position + 2]]
                position += 4
            }
            result[position] == 3 -> {
                result[ result[position + 1] ] = readLine()!!.toInt()
                position += 2
            }
            result[position] == 4 -> {
                println(result[ result[position + 1]])
                position += 2
            }
            result[position] == 5 -> {
                if(result[position + 1] != 0) {
                    position = result[position + 2]
                } else {
                    position += 3
                }
            }
            result[position] == 6 -> {
                if(result[position + 1] == 0) {
                    position = result[position + 2]
                } else {
                    position += 3
                }
            }
            result[position] == 7 -> {
                if(result[position + 1] < result[position + 2]) {
                    result[ result[position + 3] ] = 1
                } else {
                    result[ result[position + 3] ] = 0
                }
                position += 4
            }
            result[position] == 8 -> {
                if(result[position + 1] == result[position + 2]) {
                    result[ result[position + 3] ] = 1
                } else {
                    result[ result[position + 3] ] = 0
                }
                position += 4
            }
        }
    }

    print("ended computation")
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