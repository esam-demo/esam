## How to run locally

###1. build
```sh
mvn clean package -DskipTests
```

### 2. server start: 3 instances

```shell script
1. java -jar target\esam-eurekasvr-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer1
2. java -jar target\esam-eurekasvr-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer2
3. java -jar target\esam-eurekasvr-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer3
```
