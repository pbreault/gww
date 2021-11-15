plugins {
    id("org.ajoberstar.reckon") version "0.13.0"
}

reckon {
    scopeFromProp()
    snapshotFromProp()
}

tasks.named("reckonTagCreate").configure {
    this.dependsOn(":app:check")
}