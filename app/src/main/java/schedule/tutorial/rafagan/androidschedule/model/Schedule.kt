package schedule.tutorial.rafagan.androidschedule.model

data class Schedule(val placeId: String, val date: String, val time: String)

fun fromScheduleToMap(schedule: Schedule): Map<String, String> {
    return mapOf(
            "placeId" to schedule.placeId,
            "date" to schedule.date,
            "time" to schedule.time
    )
}

fun fromMapToSchedule(schedule: Map<String, Any>): Schedule {
    return Schedule(
            (schedule["placeId"] as? String) ?: "",
            (schedule["date"] as? String) ?: "",
            (schedule["time"] as? String) ?: ""
    )
}