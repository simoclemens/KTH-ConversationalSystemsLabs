package furhatos.app.fruitsellerskill.flow.main

import furhatos.app.fruitsellerskill.flow.Parent
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.app.fruitsellerskill.nlu.BuyFruit
import furhatos.app.fruitsellerskill.nlu.RequestOptions
import furhatos.app.fruitsellerskill.nlu.Fruit
import furhatos.flow.kotlin.*
import furhatos.util.Language

val Options = state(Parent) {
    onResponse<BuyFruit> {
        val fruits = it.intent.fruits
        if (fruits != null) {
            goto(orderReceived(fruits))
        }
        else {
            propagate()
        }
    }

    onResponse<RequestOptions> {
        furhat.say("We have ${Fruit().getEnum(Language.ENGLISH_US).joinToString(", ")}")
        furhat.say("You can choose between our list. You can specify the kind of fruit and the desired quantity.")
        furhat.ask("Did you find something interesting?")
    }

    onResponse<Yes> {
        furhat.say("You can specify the kind of fruit you want and the desired quantity.")
        furhat.ask {
            random {
                +"What kind of fruit do you want?"
                +"What type of fruit?"
            }
        }
    }
}

