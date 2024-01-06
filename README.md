This application is meant for message handling (API)
Uses web sockets to communicate with front-end

Flow of the application;
when retreiving messages from front-end; the application stores the message in database as well as sends the message forward to the intenden recipient (through ID)
when retreiving messages from front-end: A kafka producer sends the message to a Kafka stream (Kafka stream is an unnessecary middle-man), and kafka stream sends it forward to a kafka-consumer;
the kafka consumer sends the message forward to the database, as well as sending the message to web-socket to forward the message to the intenden recipient.
When a front-end client calls GET-messages, then no Kafka is used at all, everything is then just retreived from the database as usual.
 
To run;
open powershell
cd InsertYourKafkaFolderHere (for me it's Cd C:\kafka_2.13-3.6.1)
Start Kafka in windows:
bin/windows/zookeeper-server-start.bat config/zookeeper.properties
then open new powershell terminal and;
bin/windows/kafka-server-start.bat config/server.properties
To stop the server;
bin/windows/kafka-server-stop.bat config/server.properties
Open a new terminal, read messages; (topic namnet is set to "algot_test");
bin/windows/kafka-console-consumer.bat --topic algot_test --from-beginning --bootstrap-server localhost:9092
Open a new terminal, send messages (topic is set to "spring.boot.kafka.stream.input"
bin/windows/kafka-console-producer.bat --topic spring.boot.kafka.stream.input --broker-list localhost:9092
 
See bransch in github for simpler example of kafka streams and producer/consumer
