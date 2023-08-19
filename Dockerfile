FROM openjdk:17-jdk

WORKDIR /app/arranginseats_backend
COPY . /app/arranginseats_backend
VOLUME /tmp

RUN chmod +x run.sh && gradle updateLib

EXPOSE 8080

CMD [ "sh" , "run.sh" ]