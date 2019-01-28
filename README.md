## Demo application for producing and consuming from ActiveMQ

### Build application
`./gradlew bootJar`

It will be located in `build/libs/amazon-mq-demo-0.1.0.jar`

### Start consumer:
`java -jar build/libs/amazon-mq-demo-0.1.0.jar --spring.profiles.active=consumer`


### Start producer:
`java -jar build/libs/amazon-mq-demo-0.1.0.jar --spring.profiles.active=producer`

### Partitioning
In order to check partitioning, you can start two consumers at the same time

To play with partitioning you can add parameters:

`--application.parallel.consumers=2 --application.total.messages=5`

This will produce 5 messages: 3 with one partition key and 2 with other. 
So if you have two consumers running each will receive only messages with same partition

### Integrity check mode
Both producer and consumer can be launched with flag
`--application.delivery.check=true`

This means that consumer will check the counter in message and compare it with a local counter.
Producer will be running infinitely

### Configuration options:
* `spring.activemq.broker-url` : Broker Url
* `spring.activemq.user` : Username
* `spring.activemq.password` : Password
* `application.queue.name` : Queue Name, default `testqueue`
* `application.producer.partitions` : How much partitions producer will create
* `application.total.messages` : How much messages producer will create, see also next property
* `application.delivery.check` : Removes limit of produced messages, Special mode for consumer, see above
