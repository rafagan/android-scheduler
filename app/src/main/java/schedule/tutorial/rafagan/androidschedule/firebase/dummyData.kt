package schedule.tutorial.rafagan.androidschedule.firebase

import schedule.tutorial.rafagan.androidschedule.model.*


fun generatePlaces(): Map<String, Map<String, String>> {
    return mapOf(
            "1" to fromPlaceToMap(Place(
                    "1",
                    "Rei da coxinha",
                    "Melhor marcenaria do Brasil. Aceitamos TeleSena.",
                    "08:00",
                    "23:30")),
            "2" to fromPlaceToMap(Place(
                    "2",
                    "Padaria do Seu Zé",
                    "Venda de equipamentos eletrônicos, bonecas de porcelanato e tênis para jogging.",
                    "13:00",
                    "15:00")),
            "3" to fromPlaceToMap(Place(
                    "3",
                    "Rei da coxinha",
                    "Melhor marcenaria do Brasil. Aceitamos TeleSena.",
                    "08:00",
                    "23:30")),
            "4" to fromPlaceToMap(Place(
                    "4",
                    "Padaria do Seu Zé",
                    "Venda de equipamentos eletrônicos, bonecas de porcelanato e tênis para jogging.",
                    "13:00",
                    "15:00")),
            "5" to fromPlaceToMap(Place(
                    "5",
                    "Rei da coxinha",
                    "Melhor marcenaria do Brasil. Aceitamos TeleSena.",
                    "08:00",
                    "23:30")))
    }

fun generateSchedules(placeId: String, date: String): Map<String, Map<String, String>> {
    return mapOf(
            "01:00" to fromScheduleToMap(Schedule(placeId, date, "01:00")),
            "06:00" to fromScheduleToMap(Schedule(placeId, date, "06:00")),
            "11:00" to fromScheduleToMap(Schedule(placeId, date, "11:00")),
            "19:00" to fromScheduleToMap(Schedule(placeId, date, "19:00")),
            "22:00" to fromScheduleToMap(Schedule(placeId, date, "22:00"))
    )
}

fun generateJobs1(placeId: String): Map<String, Map<String, Any>> {
    return mapOf(
            "1" to fromJobToMap(Job(placeId, "1", "Pintura")),
            "2" to fromJobToMap(Job(placeId, "2", "Dança do ventre"))
    )
}

fun generateJobs2(placeId: String): Map<String, Map<String, Any>> {
    return mapOf(
            "1" to fromJobToMap(Job(placeId, "1", "Cartografia")),
            "2" to fromJobToMap(Job(placeId, "2", "Corte a laser")),
            "3" to fromJobToMap(Job(placeId, "2", "Bistrô"))
    )
}


fun generateDatabase() {
    val connection = Database.createConnection()
    val places = generatePlaces()

    val content = mapOf("database" to mapOf("places" to places))
    connection.setValue(content).addOnSuccessListener {
        val placesRef = connection.child("database").child("places")
        val placesRef1 = placesRef.child("1")
        val placesRef2 = placesRef.child("2")

//        placesRef1
//                .child("schedules")
//                .child("2018-10-19")
//                .setValue(generateSchedules("1", "2018-10-19"))
//        placesRef2
//                .child("schedules")
//                .child("2018-10-18")
//                .setValue(generateSchedules("2", "2018-10-18"))

        placesRef1
                .child("jobs")
                .setValue(generateJobs1("1"))
        placesRef2
                .child("jobs")
                .setValue(generateJobs2("2"))
    }
}
