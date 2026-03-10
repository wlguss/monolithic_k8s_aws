# jar 파일로 패키징하기 위한 jdk image pull
FROM eclipse-temurin:17-jdk-alpine

# jar 파일의 위치 명시 
ARG JAR_FILE=build/libs/*.jar

# copy 
COPY ${JAR_FILE} ./backend.jar 

# run (컨테이너에서 파일 실행)
ENTRYPOINT [ "java", "-jar", "./backend.jar" ]