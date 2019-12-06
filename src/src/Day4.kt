package src

fun main() {
    var passwordMin = 272091
    var passwordMax = 815432

    var resultArray = ArrayList<Int>()
    var allOptionsCount = 0

    for(pass in passwordMin..passwordMax){
        var charArray = pass.toString().trim().split("")
        var digits = ArrayList<Int>()

        for(i in 1 until charArray.size -1){
            digits.add(charArray[i].toInt())
        }

        if( digits[5] >= digits[4]
            && digits[4] >= digits[3]
            && digits[3] >= digits[2]
            && digits[2] >= digits[1]
            && digits[1] >= digits[0] &&
            ( digits[0] == digits[1]
                    || digits[1] == digits[2]
                    || digits[2] == digits[3]
                    || digits[3] == digits[4]
                    || digits[4] == digits[5])) {


           if (digits[0] == digits[1] && digits[1] != digits[2]
                || digits[1] == digits[2] && digits[2] != digits[3] && digits[1] != digits[0]
                || digits[2] == digits[3] && digits[3] != digits[4] && digits[2] != digits[1]
                || digits[3] == digits[4] && digits[4] != digits[5] && digits[3] != digits[2]
                || digits[4] == digits[5] && digits[4] != digits[3] ) {
                allOptionsCount++
            }
        }
    }

    println(allOptionsCount)
    println(resultArray)
}

