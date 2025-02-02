// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
    id("com.google.dagger.hilt.android") version "2.51" apply false
    id("com.vanniktech.dependency.graph.generator") version("0.8.0")
    kotlin("jvm") version "1.6.10"
}
