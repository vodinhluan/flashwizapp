HOW TO USE PROJECT? 

1. Clone Project
git clone -c core.protectNTFS=false https://github.com/vodinhluan/flashwizapp.git --branch master

2. Change password in application.properties
3. Open build.gradle -> Check version in dependencies. If version is 8.0.29
  implementation group: 'mysql', name: 'mysql-connector-java', version: '8.0.29'
  and download jar file in https://mvnrepository.com/artifact/mysql/mysql-connector-java
  then Add External Jars  in project
4. Run
