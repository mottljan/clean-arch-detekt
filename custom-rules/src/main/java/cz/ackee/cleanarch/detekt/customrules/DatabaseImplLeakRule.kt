package cz.ackee.cleanarch.detekt.customrules

import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.kotlin.psi.KtImportDirective

class DatabaseImplLeakRule(config: Config) : Rule(config) {

    override val issue = Issue(
        id = "DatabaseImplLeak",
        description = "Database implementation leaks outside of the 'db' package. This violates dependency rule.",
        severity = Severity.CodeSmell,
        debt = Debt.FIVE_MINS
    )

    override fun visitImportDirective(importDirective: KtImportDirective) {
        val import = importDirective.importPath?.pathStr
        println(import)
        if (import?.contains("room") == true) {
            report(CodeSmell(
                issue = issue,
                entity = Entity.from(importDirective),
                message = "Importing '$import' which is part of the database implementation."
            ))
        }
    }
}