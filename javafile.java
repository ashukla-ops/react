Service Registration with Eureka

@EnableEurekaClient

eureka:
   client:
      serviceUrl:
         defaultZone: http://localhost:8761/eureka
		 
spring:
   application:
      name: eurekaclient


@SpringBootApplication
@EnableEurekaClient
@RestController

###################################################
Spring Boot - Zuul Proxy Server and Routing
@SpringBootApplication
@EnableZuulProxy

<dependency>
   <groupId>org.springframework.cloud</groupId>
   <artifactId>spring-cloud-starter-zuul</artifactId>
</dependency>

spring.application.name = zuulserver
 zuul.routes.products.path = /api/demo/**
  zuul.routes.products.url = http://localhost:8080/
server.port = 8111

#########################################################

Creating Spring Cloud Configuration Server
<dependency>
   <groupId>org.springframework.cloud</groupId>
   <artifactId>spring-cloud-config-server</artifactId>
</dependency>

@SpringBootApplication
@EnableConfigServer

server.port = 8888
spring.cloud.config.server.native.searchLocations=file:///C:/configprop/
SPRING_PROFILES_ACTIVE=native
##########################################################

Working with Spring Cloud Configuration Server
<dependency>
   <groupId>org.springframework.cloud</groupId>
   <artifactId>spring-cloud-starter-config</artifactId>
</dependency>

@SpringBootApplication
@RefreshScope

spring.application.name = config-client
spring.cloud.config.uri = http://localhost:8888


##################################################################
Enabling Spring Boot Actuator
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

management:
   security:
      enabled: false
	  
	  management.port = 9000
	  
/metrics	To view the application metrics such as memory used, memory free, threads, classes, system uptime etc.
/env	To view the list of Environment variables used in the application.
/beans	To view the Spring beans and its types, scopes and dependency.
/health	To view the application health
/info	To view the information about the Spring Boot application.
/trace	To view the list of Traces of your Rest endpoints.


#######################################################################
Spring Boot - Admin Server---
Monitoring your application by using Spring Boot Actuator Endpoint is slightly difficult
. Because, if you have ‘n’ number of applications, every application has separate actuator
 endpoints, thus making monitoring difficult. Spring Boot Admin Server is an application used to 
 manage and monitor your Microservice application.
 
 <dependency>
   <groupId>de.codecentric</groupId>
   <artifactId>spring-boot-admin-server</artifactId>
   <version>1.5.5</version>
</dependency>
<dependency>
   <groupId>de.codecentric</groupId>
   <artifactId>spring-boot-admin-server-ui</artifactId>
   <version>1.5.5</version>
</dependency>
@EnableAdminServer
server.port = 9090
spring.application.name = adminserver

#############################################################################
Spring Boot - Admin Client
For monitoring and managing your microservice application via Spring Boot Admin Server, you should add the Spring Boot Admin starter 
client dependency and point out the Admin Server URI into the application properties file.

<dependency>
   <groupId>de.codecentric</groupId>
   <artifactId>spring-boot-admin-starter-client</artifactId>
   <version>1.5.5</version>
</dependency>
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
spring.boot.admin.url = http://localhost:9090/



###########################################
Spring Boot - Enabling Swagger2
<dependency>
   <groupId>io.springfox</groupId>
   <artifactId>springfox-swagger2</artifactId>
   <version>2.7.0</version>
</dependency>
<dependency>
   <groupId>io.springfox</groupId>
   <artifactId>springfox-swagger-ui</artifactId>
   <version>2.7.0</version>
</dependency>

@EnableSwagger2

@Bean
   public Docket productApi() {
      return new Docket(DocumentationType.SWAGGER_2).select()
         .apis(RequestHandlerSelectors.basePackage("com.tutorialspoint.swaggerdemo")).build();
   }
   http://localhost:8080/swagger-ui.html
   
 ########################################################
   Dockerfile
   
   
FROM java:8
VOLUME /tmp
ADD dockerapp-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

mvn package docker:build

###############################################################
Spring Cloud Sleuth
[application-name,traceid,spanid,zipkin-export]
<dependency>
   <groupId>org.springframework.cloud</groupId>
   <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>

###################################################################
Zipkin Server
Zipkin is an application that monitors and manages the Spring Cloud Sleuth logs of your 
Spring Boot application. To build a Zipkin server, 
we need to add the Zipkin UI and Zipkin Server dependencies in our build configuration file.

<dependency>
   <groupId>io.zipkin.java</groupId>
   <artifactId>zipkin-server</artifactId>
</dependency>
<dependency>
   <groupId>io.zipkin.java</groupId>
   <artifactId>zipkin-autoconfigure-ui</artifactId>
</dependency>
server.port = 9411

@SpringBootApplication
@EnableZipkinServer

logs via Zipkin UI.
<dependency>
   <groupId>org.springframework.cloud</groupId>
   <artifactId>spring-cloud-sleuth-zipkin</artifactId>
</dependency>

Now, add the Always Sampler Bean in your Spring Boot application to export the logs into Zipkin server.
@Bean
public AlwaysSampler defaultSampler() {
   return new AlwaysSampler();
}

########################################
Spring Boot - Hystrix
<dependency>
   <groupId>org.springframework.cloud</groupId>
   <artifactId>spring-cloud-starter-hystrix</artifactId>
</dependency>
@SpringBootApplication
@EnableHystrix


@RequestMapping(value = "/")
@HystrixCommand(fallbackMethod = "fallback_hello", commandProperties = {
   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
})
public String hello() throws InterruptedException {
   Thread.sleep(3000);
   return "Welcome Hystrix";
}
private String fallback_hello() {
   return "Request fails. It takes long time to response";
}