This application is meant for message handling (API) and was built mostly for experimentation <br>
Uses web sockets to communicate with front-end<br>
Has unit tests <br>

<br>
Flow of the application; <br>
when retreiving messages from front-end; the application stores the message in database as well as sends the message forward to the intenden recipient (through ID)<br>
when retreiving messages from front-end: A kafka producer sends the message to a Kafka stream (Kafka stream is an unnessecary middle-man), and kafka stream sends it forward to a kafka-consumer;<br>
the kafka consumer sends the message forward to the database, as well as sending the message to web-socket to forward the message to the intenden recipient.<br>
When a front-end client calls GET-messages, then no Kafka is used at all, everything is then just retreived from the database as usual.<br>
 <br>
To run;<br>
open powershell<br>
cd InsertYourKafkaFolderHere (for me it's Cd C:\kafka_2.13-3.6.1)<br>
Start Kafka in windows:<br>
bin/windows/zookeeper-server-start.bat config/zookeeper.properties<br>
then open new powershell terminal and;<br>
bin/windows/kafka-server-start.bat config/server.properties<br>
To stop the server;<br>
bin/windows/kafka-server-stop.bat config/server.properties<br>
Open a new terminal, read messages; (topic namnet is set to "algot_test");<br>
bin/windows/kafka-console-consumer.bat --topic algot_test --from-beginning --bootstrap-server localhost:9092<br>
Open a new terminal, send messages (topic is set to "spring.boot.kafka.stream.input"<br>
bin/windows/kafka-console-producer.bat --topic spring.boot.kafka.stream.input --broker-list localhost:9092<br>
 <br>
See bransch in github for simpler example of kafka streams and producer/consumer<br>
