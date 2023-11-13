package furhatos.app.fruitsellerskill.flow.main

import furhatos.app.fruitsellerskill.nlu.FruitList
import furhatos.nlu.common.Time
import furhatos.records.User

class FruitData (
    var fruits : FruitList = FruitList()
)

val User.order : FruitData
    get() = data.getOrPut(FruitData::class.qualifiedName, FruitData())

var User.deliveryTime : Time
    get() = data.getOrPut(Time::class.qualifiedName, Time())
    set(value) = data.put(Time::class.qualifiedName, value)