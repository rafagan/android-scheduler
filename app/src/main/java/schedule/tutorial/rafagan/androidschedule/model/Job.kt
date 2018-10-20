package schedule.tutorial.rafagan.androidschedule.model

data class Job(
        val placeId: String,
        val jobId: String,
        val name: String,
        var isChecked:Boolean = false)

fun fromJobToMap(job: Job): Map<String, String> {
    return mapOf(
            "placeId" to job.placeId,
            "jobId" to job.jobId,
            "name" to job.name
    )
}

fun fromMapToJob(job: Map<String, String>): Job {
    return Job(
            job["placeId"] ?: "",
            job["jobId"] ?: "",
            job["name"] ?: ""
    )
}

fun fromJobToMapToSchedule(job: Job): Map<String, String> {
    return mapOf(
            "jobId" to job.jobId,
            "name" to job.name
    )
}