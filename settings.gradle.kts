plugins {
    id("ca.stellardrift.polyglot-version-catalogs") version "6.1.0"
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "corn-parent"

basicProjects("misc", "context", "properties")
minecraftProjects("spigot", "paper")

fun basicProjects(vararg names: String) {
    include(*names)

    names.forEach {
        project(":$it").name = "corn-$it"
    }
}

fun minecraftProjects(vararg names: String) {
    include(*names)

    names.forEach {
        project(":$it").apply {
            projectDir = file("minecraft/$it")
            name = "corn-minecraft-$it"
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
