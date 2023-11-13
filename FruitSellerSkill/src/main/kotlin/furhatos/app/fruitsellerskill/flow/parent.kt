package furhatos.app.fruitsellerskill.flow

import furhatos.app.fruitsellerskill.flow.main.Idle
import furhatos.flow.kotlin.*
import java.util.*

val usersQueue: Queue<furhatos.records.User> = LinkedList()

val Parent: State = state {

    onUserEnter() {
        when { // "it" is the user that entered
            furhat.isAttendingUser -> {
                var oldUser = furhat.users.current
                furhat.attend(it) // Attend user if not attending anyone
                furhat.say("Please wait, I will be there shortly!")
                furhat.attend(oldUser)
                reentry()
            }


        }
    }

    onUserLeave(instant = true) {
        when {
            !users.hasAny() -> { // last user left
                furhat.attendNobody()
                goto(Idle)
            }
            furhat.isAttending(it) -> furhat.attend(users.other) // current user left
            !furhat.isAttending(it) -> furhat.glance(it.head.location) // other user left, just glance
        }
    }

}
