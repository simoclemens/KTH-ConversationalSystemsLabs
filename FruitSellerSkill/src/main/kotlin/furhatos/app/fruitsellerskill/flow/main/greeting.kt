package furhatos.app.fruitsellerskill.flow.main

import furhatos.app.fruitsellerskill.flow.Parent
import furhatos.app.fruitsellerskill.flow.main.TakingOrder
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

val Greeting = state(Parent) {
    onEntry {
        furhat.say {
            random {
                +"Hi there"
                +"Oh, hello there"
                +"Hi, welcome to the fruit shop"
            }

        }
        furhat.say ("I am a ChatBot here to assist you with your fruit order!")
        goto(TakingOrder)
    }
}

