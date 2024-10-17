plugins {
    java
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    java.sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
//    Lombok
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

//    fasterXML
    implementation("com.fasterxml.jackson.core:jackson-core:2.17.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")

//    JUnit
    testImplementation("junit:junit:3.8.1")
}