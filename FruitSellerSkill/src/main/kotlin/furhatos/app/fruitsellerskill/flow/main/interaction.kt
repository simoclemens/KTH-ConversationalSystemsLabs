package furhatos.app.fruitsellerskill.flow.main

import furhatos.app.fruitsellerskill.flow.Parent
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.app.fruitsellerskill.nlu.BuyFruit
import furhatos.app.fruitsellerskill.nlu.RequestOptions
import furhatos.app.fruitsellerskill.nlu.Fruit
import furhatos.app.fruitsellerskill.nlu.FruitList
import furhatos.flow.kotlin.*
import furhatos.util.Language

val TakingOrder = state(parent = Options) {
    onEntry {
        furhat.ask {
            random {
                +"How about some fruits?"
                +"Do you want some fruits?"
            }
        }
    }

    onResponse<No> {
        furhat.say("Okay, that's a shame. Have a splendid day!")
        goto(Idle)
    }
}

fun orderReceived(fruitList: FruitList) : State = state(Options) {
    onEntry {
        furhat.say("${fruitList.text}, what a lovely choice!")
        println("a")
        fruitList.list.forEach {
            users.current.order.fruits.list.add(it)
        }
        println("b")
        furhat.ask("Anything else?")
    }

    onReentry {
        furhat.ask("Did you want something else?")
    }

    onResponse<No> {
        furhat.say("Okay, here is your order of ${users.current.order.fruits}. Have a great day!")
        goto(Idle)
    }
}