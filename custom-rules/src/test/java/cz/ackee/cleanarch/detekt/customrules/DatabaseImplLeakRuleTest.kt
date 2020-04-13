package cz.ackee.cleanarch.detekt.customrules

import cz.ackee.cleanarch.detekt.customrules.DatabaseImplLeakRule.Companion.DATABASE_LAYER_PACKAGE
import cz.ackee.cleanarch.detekt.customrules.DatabaseImplLeakRule.Companion.DATABASE_LIBRARY_PACKAGE_PATH
import cz.ackee.cleanarch.detekt.customrules.DatabaseImplLeakRule.Companion.KOIN_MODULE_PACKAGE_PATH
import io.gitlab.arturbosch.detekt.test.FindingsAssert
import io.gitlab.arturbosch.detekt.test.assertThat
import io.gitlab.arturbosch.detekt.test.lint
import org.junit.Test

class DatabaseImplLeakRuleTest {

    private val rule = DatabaseImplLeakRule()

    private val databaseLayerImport = "cz.ackee.cleanarch.detekt.features.articles.data.$DATABASE_LAYER_PACKAGE.dao"

    @Test
    fun `report database library dependency outside of database layer`() {
        testReporting(dbImplImport = DATABASE_LIBRARY_PACKAGE_PATH)
    }

    @Test
    fun `report database layer dependency outside of database layer`() {
        testReporting(dbImplImport = databaseLayerImport)
    }

    @Test
    fun `does not report database library dependency inside database layer`() {
        testNotReporting(filePackageEnd = DATABASE_LAYER_PACKAGE, dbImplImport = DATABASE_LIBRARY_PACKAGE_PATH)
    }

    @Test
    fun `does not report database layer dependency inside database layer`() {
        testNotReporting(filePackageEnd = DATABASE_LAYER_PACKAGE, dbImplImport = databaseLayerImport)
    }

    @Test
    fun `does not report database library dependency inside file with koin module definitions`() {
        testNotReporting(
            filePackageEnd = "",
            dbImplImport = DATABASE_LIBRARY_PACKAGE_PATH,
            otherImport = KOIN_MODULE_PACKAGE_PATH
        )
    }

    @Test
    fun `does not report database layer dependency inside file with koin module definitions`() {
        testNotReporting(
            filePackageEnd = "",
            dbImplImport = DATABASE_LAYER_PACKAGE,
            otherImport = KOIN_MODULE_PACKAGE_PATH
        )
    }

    private fun testReporting(dbImplImport: String) {
        testImport(getCode(dbImplImport = dbImplImport)) { hasSize(1) }
    }

    private fun testNotReporting(
        filePackageEnd: String,
        dbImplImport: String,
        otherImport: String = ""
    ) {
        testImport(getCode(
            filePackageEnd = filePackageEnd,
            dbImplImport = dbImplImport,
            otherImport = otherImport
        )) { isEmpty() }
    }

    private fun testImport(code: String, isCorrect: FindingsAssert.() -> Unit) {
        val findings = rule.lint(code)

        assertThat(findings).isCorrect()
    }

    private fun getCode(
        filePackageEnd: String = "data",
        dbImplImport: String,
        otherImport: String = ""
    ): String {
        return """
            package cz.ackee.cleanarch.detekt.features.articles.$filePackageEnd

            import $dbImplImport
            import $otherImport
        """.trimIndent()
    }
}