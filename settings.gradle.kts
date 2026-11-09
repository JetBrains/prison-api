pluginManagement {
  val kotlinVersion =
    providers.gradleProperty("kotlin_version")
      .orElse("2.4.21")
      .get()

  val kotlinRepoUrl =
    providers.gradleProperty("kotlin_repo_url").orNull

  repositories {
    if (!kotlinRepoUrl.isNullOrBlank()) {
      maven(kotlinRepoUrl)
    }
    gradlePluginPortal()
    mavenCentral()
  }

  plugins {
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("plugin.lombok") version kotlinVersion
  }
}

val kotlinRepoUrl =
  providers.gradleProperty("kotlin_repo_url").orNull

dependencyResolutionManagement {
  repositories {
    if (!kotlinRepoUrl.isNullOrBlank()) {
      exclusiveContent {
        forRepository {
          maven(kotlinRepoUrl)
        }
        filter {
          includeGroupByRegex("org\\.jetbrains\\.kotlin.*")
        }
      }
    }
    mavenCentral()
  }
}

rootProject.name = "prison-api"
