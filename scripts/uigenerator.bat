#https://www.baeldung.com/spring-boot-rest-client-swagger-codegen

java -jar .\openapi-generator-cli-6.6.0.jar generate^
 -i .\api.yaml -g spring^
 -p java17=true^
 --artifact-id ui-client^
 -o ../generated/ui-client

pause