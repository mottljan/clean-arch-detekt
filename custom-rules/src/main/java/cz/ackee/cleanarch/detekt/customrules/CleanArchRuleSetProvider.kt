package cz.ackee.cleanarch.detekt.customrules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class CleanArchRuleSetProvider : RuleSetProvider {

    override val ruleSetId = "clean-arch-rules"

    override fun instance(config: Config) = RuleSet(ruleSetId, listOf(DatabaseImplLeakRule(config)))
}