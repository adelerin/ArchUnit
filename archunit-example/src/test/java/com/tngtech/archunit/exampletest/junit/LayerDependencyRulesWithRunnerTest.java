package com.tngtech.archunit.exampletest.junit;

import com.tngtech.archunit.junit.AnalyseClasses;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.core.JavaClass.Predicates.resideInPackage;
import static com.tngtech.archunit.lang.ArchRule.Definition.all;
import static com.tngtech.archunit.lang.ArchRule.Definition.classes;
import static com.tngtech.archunit.lang.conditions.ArchConditions.accessClassesThatResideIn;
import static com.tngtech.archunit.lang.conditions.ArchConditions.never;
import static com.tngtech.archunit.lang.conditions.ArchConditions.onlyBeAccessedByAnyPackage;

@ArchIgnore
@RunWith(ArchUnitRunner.class)
@AnalyseClasses(packages = "com.tngtech.archunit.example")
public class LayerDependencyRulesWithRunnerTest {

    @ArchTest
    public static final ArchRule services_should_not_access_controllers =
            all(classes().that(resideInPackage("..service..")))
                    .should(never(accessClassesThatResideIn("..controller..")));

    @ArchTest
    public static final ArchRule persistence_should_not_access_services =
            all(classes().that(resideInPackage("..persistence..")))
                    .should(never(accessClassesThatResideIn("..service..")));

    @ArchTest
    public static final ArchRule services_should_only_be_accessed_by_controllers_or_other_services =
            all(classes().that(resideInPackage("..service..")))
                    .should(onlyBeAccessedByAnyPackage("..controller..", "..service.."));
}
