# spring-boot
## Technical:

1. Framework: Spring Boot v2.5.0
2. Java 17
3. Thymeleaf v2.5.0
4. Bootstrap v.4.3.1
5. Lombok v1.18.20
6. AssertJ v3.18.9


## Setup with Intellij IDE
1. Create project from Initializr: File > New > project > Spring Initializr
2. Add lib repository into pom.xml
3. Add folders
    - Source root: src/main/java
    - View: src/main/resources
    - Static: src/main/resource/static
4. Create database with name "demo" as configuration in application.properties
5. Run sql script to create table doc/data.sql

## Implement a Feature
1. Create mapping domain class and place in package com.nnk.springboot.domain
2. Create repository class and place in package com.nnk.springboot.repositories
3. Create controller class and place in package com.nnk.springboot.controllers
4. Create view files and place in src/main/resource/templates

## Write Unit Test
1. Create unit test and place in package com.nnk.springboot in folder test > java

## Security
1. Create user service to load user from  database and place in package com.nnk.springboot.services
2. Add configuration class and place in package com.nnk.springboot.config
