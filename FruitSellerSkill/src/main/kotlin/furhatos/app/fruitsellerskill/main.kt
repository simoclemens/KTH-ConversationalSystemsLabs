package furhatos.app.fruitsellerskill

import furhatos.app.fruitsellerskill.flow.Init
import furhatos.flow.kotlin.Flow
import furhatos.skills.Skill

class FruitsellerskillSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
