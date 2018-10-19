package schedule.tutorial.rafagan.androidschedule.model

data class Place(
        val id: String = "",
        val name: String = "",
        val description: String = "",
        val openTime: String = "",
        val closeTime: String = "")

fun fromPlaceToMap(place: Place): Map<String, String> {
    return mapOf(
            "id" to place.id,
            "name" to place.name,
            "description" to place.description,
            "openTime" to place.openTime,
            "closeTime" to place.closeTime
    )
}

fun fromMapToPlace(place: Map<String, String>): Place {
    return Place(
            place["id"] ?: "",
            place["name"] ?: "",
            place["description"] ?: "",
            place["openTime"] ?: "",
            place["closeTime"] ?: ""
    )
}