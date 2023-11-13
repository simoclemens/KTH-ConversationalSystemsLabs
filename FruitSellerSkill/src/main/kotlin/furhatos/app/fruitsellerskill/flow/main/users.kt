package furhatos.app.fruitsellerskill.flow.main

import furhatos.app.fruitsellerskill.nlu.FruitList
import furhatos.records.User

class FruitData (
    var fruits : FruitList = FruitList()
)

val User.order : FruitData
    get() = data.getOrPut(FruitData::class.qualifiedName, FruitData())