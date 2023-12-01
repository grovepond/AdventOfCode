import java.io.File

class Util
{
    companion object {
    fun readInput(fileName: String): List<String>
            = File(fileName).useLines { it.toList() }
    }
}