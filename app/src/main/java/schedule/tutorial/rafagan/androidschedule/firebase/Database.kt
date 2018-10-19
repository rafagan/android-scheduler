package schedule.tutorial.rafagan.androidschedule.firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class Database {
    companion object {
        fun createConnection(): DatabaseReference {
            return FirebaseDatabase.getInstance().reference
        }
    }
}