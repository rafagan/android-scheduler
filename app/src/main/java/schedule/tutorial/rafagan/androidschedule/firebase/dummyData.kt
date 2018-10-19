package schedule.tutorial.rafagan.androidschedule.firebase

import schedule.tutorial.rafagan.androidschedule.model.Place
import schedule.tutorial.rafagan.androidschedule.model.Schedule
import schedule.tutorial.rafagan.androidschedule.model.fromPlaceToMap


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

fun generateSchedules(placeId: String): List<String> {
    return listOf(
            Schedule(placeId, "01:00"),
            Schedule(placeId, "06:00"),
            Schedule(placeId, "11:00"),
            Schedule(placeId, "19:00"),
            Schedule(placeId, "22:00")
    ).map { it.time }
}


fun generateDatabase() {
    val connection = Database.createConnection()
    val places = generatePlaces()

    val content = mapOf("database" to mapOf("places" to places))
    connection.setValue(content).addOnSuccessListener {
        val placesRef = connection.child("database").child("places")
        placesRef.child("1").child("schedules").setValue(generateSchedules("1"))
        placesRef.child("2").child("schedules").setValue(generateSchedules("2"))
    }
}
