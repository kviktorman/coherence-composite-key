# 1. Repository intro

The repository contains an example of a coherence extend client. This extend client uses coherence-hibernate to store data to a coherence cache server. The sample code uses an H2 database.

It has 3 API calls, all of them are defined as HTTP GET.

| Description | URL |
|---|---|
|Populate Data|`http://localhost:8080/populate-database`|
|Retrieve Simple key object|`http://localhost:8080/get-simple-key-object`|
|Retrieve Composite key object|`http://localhost:8080/get-composite-key-object`|

Currently the example is linked to a server that runs on 127.0.0.1 and on port 7574. This you can change in the `src/main/resources/coherence-extend-client-config.xml` file.

# 2. Use cases 

## 2.1 populate database and check if cache is used

**STEP-1 Populate the the database** 
Run the application and call the `http://localhost:8080/populate-database` API.

Please keep in mind that whenever you populate the database in this example you will write to the cache as well!!!

```
2021-03-17 13:54:54.755  INFO 17126 --- [nio-8080-exec-1] i.StatisticalLoggingSessionEventListener : Session Metrics {
    351164 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    33282977 nanoseconds spent preparing 4 JDBC statements;
    12514820 nanoseconds spent executing 4 JDBC statements;
    0 nanoseconds spent executing 0 JDBC batches;
    182560148 nanoseconds spent performing 8 L2C puts;
    0 nanoseconds spent performing 0 L2C hits;
    100274118 nanoseconds spent performing 4 L2C misses;
    150837771 nanoseconds spent executing 1 flushes (flushing a total of 2 entities and 0 collections);
    0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)
```
**STEP-2 Retrieve the simple key object from cache** 

Call the `http://localhost:8080/get-simple-key-object` API, in the logs you will see that it will return the information from the cache, because the population method created the object in the database and in the cache.

```
2021-03-17 13:55:22.991  INFO 17126 --- [nio-8080-exec-4] i.StatisticalLoggingSessionEventListener : Session Metrics {
    122626 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    0 nanoseconds spent preparing 0 JDBC statements;
    0 nanoseconds spent executing 0 JDBC statements;
    0 nanoseconds spent executing 0 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    5400220 nanoseconds spent performing 1 L2C hits;
    0 nanoseconds spent performing 0 L2C misses;
    148564 nanoseconds spent executing 1 flushes (flushing a total of 1 entities and 0 collections);
    0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)
}
```
**STEP-3 Retrieve the composite key object from cache** 

Call the `http://localhost:8080/get-composite-key-object` API, in the logs you will see that it will return the information from the cache, because the population method created the object in the database and in the cache.

```
2021-03-17 13:56:57.878  INFO 17126 --- [nio-8080-exec-6] i.StatisticalLoggingSessionEventListener : Session Metrics {
    82025 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    0 nanoseconds spent preparing 0 JDBC statements;
    0 nanoseconds spent executing 0 JDBC statements;
    0 nanoseconds spent executing 0 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    6572523 nanoseconds spent performing 1 L2C hits;
    0 nanoseconds spent performing 0 L2C misses;
    204665 nanoseconds spent executing 1 flushes (flushing a total of 1 entities and 0 collections);
    0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)
}
```

## 2.2 load simple key object from cache with (restarted/another) client
You can use the same client that populated the cache, the only thing which you have to do for that is to restart the client while the cache server is still running. With this the data will be kept in the cache server, but your client will completely forget about everything. Now call the simple key API using the following link: `http://localhost:8080/get-simple-key-object`. What you will see is that the object is loaded from the cache. 
**Note, that** since you did not run the db init, the database is empty!

```
2021-03-17 13:58:58.376  INFO 17296 --- [nio-8080-exec-1] i.StatisticalLoggingSessionEventListener : Session Metrics {
    380403 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    0 nanoseconds spent preparing 0 JDBC statements;
    0 nanoseconds spent executing 0 JDBC statements;
    0 nanoseconds spent executing 0 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    37917074 nanoseconds spent performing 1 L2C hits;
    0 nanoseconds spent performing 0 L2C misses;
    11583164 nanoseconds spent executing 1 flushes (flushing a total of 1 entities and 0 collections);
    0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)
}
```
## 2.2 load simcomposite key object from cache with (restarted/another) client

You can use the same client that populated the cache, the only thing which you have to do for that is to restart the client while the cache server is still running. With this the data will be kept in the cache server, but your client will completely forget about everything. Now call the composite key API using the following link: `http://localhost:8080/get-composite-key-object`. What you will see is that the application will try to read it from the cache, but will not find it. Then it will try to read it from the database, but since it is not populated it will crash!

The hashCode method in OperationModelPK will be hit 2 times, once for the coherence cache search and once for database search. 

```
2021-03-17 14:00:51.429  INFO 17425 --- [nio-8080-exec-1] i.StatisticalLoggingSessionEventListener : Session Metrics {
    386840 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    6053366 nanoseconds spent preparing 1 JDBC statements;
    9332778 nanoseconds spent executing 1 JDBC statements;
    0 nanoseconds spent executing 0 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    0 nanoseconds spent performing 0 L2C hits;
    59337748 nanoseconds spent performing 1 L2C misses;
    0 nanoseconds spent executing 0 flushes (flushing a total of 0 entities and 0 collections);
    0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)
}
2021-03-17 14:00:51.448 ERROR 17425 --- [nio-8080-exec-1] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is javax.persistence.EntityNotFoundException: Unable to find com.example.coherence_composite_key.ModelsDAO.OperationModelDAO with id com.example.coherence_composite_key.ModelsDAO.OperationModelPK@4ad] with root cause

javax.persistence.EntityNotFoundException: Unable to find com.example.coherence_composite_key.ModelsDAO.OperationModelDAO with id com.example.coherence_composite_key.ModelsDAO.OperationModelPK@4ad
	...
```
