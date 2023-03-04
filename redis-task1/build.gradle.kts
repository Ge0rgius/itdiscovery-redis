   dependencies {
        implementation("org.hibernate.orm:hibernate-jcache")
        runtimeOnly("org.ehcache:ehcache:3.10.8")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        runtimeOnly("com.h2database:h2")
        runtimeOnly("com.mysql:mysql-connector-j")
        runtimeOnly("org.postgresql:postgresql")                
   } 

