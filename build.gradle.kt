group 'me.jcala.tip'
version '1.0-SNAPSHOT'

/*sourceSets {
    main.java.srcDirs = []
    main.kotlin.srcDirs = ['src/main/java', 'src/main/kotlin']
    sourceSets.main.groovy.srcDirs = ["src/main/java", "src/main/groovy"]
    main.resources.srcDirs = ['src/main/resources']
}*/

buildscript {
    ext {
        kotlin_version = '1.1.3-2'
        springBootVersion = '2.0.0.M3'
        ehcacheVersion = '2.10.4'
    }
    repositories {
        mavenLocal()
        mavenCentral()
        maven {
            url 'https://repo.spring.io/libs-milestone'
        }
    }
    dependencies {
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    }
}

apply plugin: 'java'
apply plugin: 'kotlin'
apply plugin: 'eclipse'
apply plugin: 'idea'
apply plugin: 'org.springframework.boot'

sourceCompatibility = 1.8

compileJava.dependsOn(processResources)
jar {
    baseName = 'tip'
    version =  '1.0-SNAPSHOT'
}
configurations {
    compile.exclude module: "velocity-tools"
    compile.exclude module: "undertow-websockets-jsr"
    compile.exclude group: 'org.apache.struts'
    compile.exclude group: 'com.fasterxml'
    compile.exclude group: 'org.hibernate'
    compile.exclude group: 'org.glassfish'
    compile.exclude group: 'dom4j'
    compile.exclude group: 'c3p0'
    compile.exclude group: 'ch.qos.logback'
}

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        url 'https://repo.spring.io/libs-milestone'
    }
}

dependencies {
    compile "org.jetbrains.kotlin:kotlin-stdlib-jre8:$kotlin_version"
    compile ("org.springframework.boot:spring-boot-starter-web:${springBootVersion}")
    compile ("org.springframework.boot:spring-boot-starter-velocity:1.4.7.RELEASE")
    testCompile ("org.springframework.boot:spring-boot-starter-test:${springBootVersion}")
    //--------------------数据库驱动----------------------------
    compile ("mysql:mysql-connector-java:5.1.38")
    //-------------------数据库连接池---------------------------
    compile ("com.zaxxer:HikariCP:2.5.1")
    //----------------------ORM--------------------------------
    compile ("org.mybatis.spring.boot:mybatis-spring-boot-starter:1.3.0")
    //--------------------JDK工具------------------------------
    compile ("org.projectlombok:lombok:1.16.18")
    //---------------------缓存-------------------------------
    compile ("net.sf.ehcache:ehcache:${ehcacheVersion}")
    //---------------------日志-------------------------------
    compile ("org.springframework.boot:spring-boot-starter-log4j2:${springBootVersion}")
    compile ("com.lmax:disruptor:3.3.6")
    //---------------------spring-boot-starter-actuator----------
    compile ("org.springframework.boot:spring-boot-starter-actuator:${springBootVersion}")
}

compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
}
compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
}