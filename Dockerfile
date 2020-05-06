FROM alpine
WORKDIR /root/data
COPY data.jar /root/data
#COPY train.csv /root/data

#Install JDK
RUN apk add openjdk8
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV PATH $PATH:$JAVA_HOME/bin

EXPOSE 9090 7777
									   

