package schedule.tutorial.rafagan.androidschedule.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Auth {
    companion object {
        private var lazyInstance: FirebaseAuth? = null

        var user: FirebaseUser? = null
            get() = instance.currentUser
            private set


        val instance: FirebaseAuth
            get() {
                if(lazyInstance == null) {
                    lazyInstance = FirebaseAuth.getInstance()
                }
                return lazyInstance!!
            }

        fun logout() {
            instance.signOut()
        }

        fun isLoggedIn(): Boolean {
            return user != null
        }
    }
}