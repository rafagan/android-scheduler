package schedule.tutorial.rafagan.androidschedule.firebase

import schedule.tutorial.rafagan.androidschedule.model.Place
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



fun generateDatabase() {
    val connection = Database.createConnection()
    val content = mapOf("database" to mapOf(
            "places" to generatePlaces()))
    connection.setValue(content)
}
