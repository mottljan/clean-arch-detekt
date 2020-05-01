package cz.ackee.cleanarch.detekt.customrules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtImportDirective

class DatabaseImplLeakRule(config: Config = Config.empty) : Rule(config) {

    companion object {

        const val DATABASE_LIBRARY_PACKAGE_PATH = "androidx.room"

        const val DATABASE_LAYER_PACKAGE = "db"

        const val KOIN_MODULE_PACKAGE_PATH = "org.koin.dsl.module"
    }

    private val KtImportDirective.import: String?
        get() = importPath?.pathStr

    override val issue = Issue(
        id = "DatabaseImplLeak",
        description = "Database implementation leaks outside of the 'db' package. This violates dependency rule.",
        severity = Severity.CodeSmell,
        debt = Debt.FIVE_MINS
    )

    override fun visitImportDirective(importDirective: KtImportDirective) {
        if (shouldReport(importDirective)) {
            report(
                CodeSmell(
                    issue = issue,
                    entity = Entity.from(importDirective),
                    message = "Importing '${importDirective.import}' which is part of the database implementation."
                )
            )
        }
    }

    private fun shouldReport(importDirective: KtImportDirective): Boolean {
        return shouldReportDbLibraryDependency(importDirective) || shouldReportDbLayerDependency(importDirective)
    }

    private fun shouldReportDbLibraryDependency(importDirective: KtImportDirective): Boolean {
        return importDirective.import.containsDbLibraryPackagePath()
                && importDirective.meetOtherRequirementsForReporting()
    }

    private fun String?.containsDbLibraryPackagePath() = this?.contains(DATABASE_LIBRARY_PACKAGE_PATH) == true

    private fun KtImportDirective.meetOtherRequirementsForReporting(): Boolean {
        return containingFileIsNotInDbLayer() && containingFileDoesNotContainKoinModules()
    }

    private fun KtImportDirective.containingFileIsNotInDbLayer(): Boolean {
        return containingKtFile.packageDirective?.qualifiedName?.contains(DATABASE_LAYER_PACKAGE) == false
    }

    private fun KtImportDirective.containingFileDoesNotContainKoinModules(): Boolean {
        return !containingKtFile.importDirectives.map { it.import }.contains(KOIN_MODULE_PACKAGE_PATH)
    }

    private fun shouldReportDbLayerDependency(importDirective: KtImportDirective): Boolean {
        return importDirective.import.containsDbLayerPackage()
                && importDirective.meetOtherRequirementsForReporting()
    }

    private fun String?.containsDbLayerPackage() = this?.contains(DATABASE_LAYER_PACKAGE) == true
}