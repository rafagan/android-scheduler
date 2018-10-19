package schedule.tutorial.rafagan.androidschedule.model

data class Schedule(val place: String, val time: String)

fun fromScheduleToMap(place: Place): Map<String, String> {
    return mapOf(
            "id" to place.id,
            "name" to place.name,
            "description" to place.description,
            "openTime" to place.openTime,
            "closeTime" to place.closeTime
    )
}

fun fromMapToSchedule(place: Map<String, String>): Place {
    return Place(
            place["id"] ?: "",
            place["name"] ?: "",
            place["description"] ?: "",
            place["openTime"] ?: "",
            place["closeTime"] ?: ""
    )
}