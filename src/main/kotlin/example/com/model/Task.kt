package example.com.model

import kotlinx.serialization.Serializable
import java.util.*

enum class Prio {
    Low, Mid, High, Vital
}

@Serializable
data class Task (
    val name: String,
    val describtion: String,
    val prio: Prio
)

fun String.capFirstLetter(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}