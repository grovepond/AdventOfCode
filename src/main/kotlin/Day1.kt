fun main() {
    val input = "input/day1.txt"
    val data = Util.readInput(input)
    println(solvePart1(data))
    println(solvePart2(data))

}

fun solvePart1 (data: List<String>): String {
    var result = 0

    data.forEach { line ->
        val number = getFirstLastDigitNumber(line)
        result += number.toInt()
    }
    return result.toString()
}

fun solvePart2 (data: List<String>): String {
    var result = 0
    data.forEach { line ->
        var tmp = line.replace("one", "o1e")
        tmp = tmp.replace("two", "t2o")
        tmp = tmp.replace("three", "t3e")
        tmp = tmp.replace("four", "f4r")
        tmp = tmp.replace("five", "f5e")
        tmp = tmp.replace("six", "s6x")
        tmp = tmp.replace("seven", "s7n")
        tmp = tmp.replace("eight", "e8t")
        tmp = tmp.replace("nine", "n9e")
        tmp = tmp.replace(Regex("[^\\d]"), "")
        val number = getFirstLastDigitNumber(tmp)
        result += number.toInt()
    }
    return result.toString()
}
fun getFirstLastDigitNumber (line: String): String{
    val firstDigit = line.filter { it.isDigit() }.first().toString()
    val lastDigit = line.filter { it.isDigit() }.last().toString()
    val number = firstDigit + lastDigit
    return number
}