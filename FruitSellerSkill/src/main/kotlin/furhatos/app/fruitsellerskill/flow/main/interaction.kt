package furhatos.app.fruitsellerskill.flow.main

import furhatos.app.fruitsellerskill.flow.Parent
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.nlu.common.Time
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
        fruitList.list.forEach {
            users.current.order.fruits.list.add(it)
        }
        furhat.ask("Anything else?")
    }

    onReentry {
        furhat.ask("Did you want something else?")
    }

    onResponse<No> {
        goto(DeliveryTime)
    }
}


val DeliveryTime = state() {

    onEntry {
        furhat.ask("When should we deliver your order?")
    }

    onResponse<Time> {
        users.current.deliveryTime = it.intent
        furhat.say("Okay, your delivery time has been set to ${users.current.deliveryTime}")
        goto(ConfirmOrder)
    }
}

val ConfirmOrder = state() {

    onEntry {
        furhat.say("Here is your order of ${users.current.order.fruits}")
        furhat.say("Your order will be ready at ${users.current.deliveryTime}")
        furhat.ask {
            random {
                +"Do you want to confirm the order?"
                +"Should I proceed with the order?"
            }
        }
    }

    onResponse<Yes> {
        furhat.say("Okay, you order has been saved. Have a nice day!")
        furhat.attend(furhat.users.other)
        goto(Greeting)
    }

    onResponse<No> {
        furhat.say("Okay, I'll cancel the order.")
        users.current.order.fruits.list.clear()
        goto(Idle)
    }
}