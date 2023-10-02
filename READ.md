To build application from the root folder run:
mvn clean install

To start application inside the docker run:

docker-compose -f docker/docker-compose.yml up -d --build application


There are two postman collections:
1) To load Product data into Kafka
2) Load personalized data into Kafka
Please run them in that sequence.

Both are using endpoints just for testing purposes.
In real life scenario, we would have a service that would be 
responsible for loading data into Kafka.

Postman collections are located in the src/test/resources/postman folder.

Data is normalized and stored in the database during ingestion we are 
making sure that there are no duplicates in the database. 
Which improves the performance. We could also create few indexes to speed up the queries.

There are also future improvements that could be done to the Kafka consumer by adding few
more topics to manage the data by data team.

Web component can also be improved by introducing cache to speed up the responses.

Pleas let me know if you have any questions.

Thank you for your time.
Roman.