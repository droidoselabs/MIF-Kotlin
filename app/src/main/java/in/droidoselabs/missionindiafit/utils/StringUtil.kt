package `in`.droidoselabs.missionindiafit.utils

/**
 * Created by android on 9/17/17.
 */
object StringUtil {
    /**
     * Method to convert string into camel case string
     *
     * @param inputString string value that need to convert into camel case
     * @return converted camel cased string
     */
    fun toCamelCase(inputString: String?): String {
        var result = ""
        if (inputString == null || inputString.isEmpty()) {
            return result
        }
        val firstChar = inputString[0]
        val firstCharToUpperCase = Character.toUpperCase(firstChar)
        result = result + firstCharToUpperCase
        for (i in 1..inputString.length - 1) {
            val currentChar = inputString[i]
            val previousChar = inputString[i - 1]
            if (previousChar == ' ') {
                val currentCharToUpperCase = Character.toUpperCase(currentChar)
                result = result + currentCharToUpperCase
            } else {
                val currentCharToLowerCase = Character.toLowerCase(currentChar)
                result = result + currentCharToLowerCase
            }
        }
        return result
    }
}