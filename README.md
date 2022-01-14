# Authentication Sample

Spring Security + Vue.js + JWT (HTTP Only) 

## Requires

 - Java 11 (Amazon Corretto)
 - Docker Desktop

## Setup

```sh
./mvnw clean
./mvnw -N install
./mvnw -N -Drun=init-container
./mvnw -f auth-migration -P migrate
./mvnw install -f auth-back -Dmaven.test.skip
```

## Example

Initialize test data
```sh
./mvnw -f auth-migration -P migrate-test
```

 - email: admin@mail.com
 - password: password
