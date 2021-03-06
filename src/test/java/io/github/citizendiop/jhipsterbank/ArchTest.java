package io.github.citizendiop.jhipsterbank;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("io.github.citizendiop.jhipsterbank");

        noClasses()
            .that()
                .resideInAnyPackage("io.github.citizendiop.jhipsterbank.service..")
            .or()
                .resideInAnyPackage("io.github.citizendiop.jhipsterbank.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..io.github.citizendiop.jhipsterbank.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
