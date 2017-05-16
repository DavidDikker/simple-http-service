# Simple HTTP Server

This example app may act as a project skeleton for [Akka HTTP](http://doc.akka.io/docs/akka-http/current/scala.html) projects.
 
This simple app runs a webserver with a single GET endpoint. The app can be run through SBT or through Docker.

To run this project locally, simply clone the repo, then compile

```
sbt clean compile test
```

Once the project is compiled and the tests finish successfully, you can run the project. To run with SBT simply use `sbt run`. Alternatively, to run with Docker you can use SBT to publish an image to your local Docker repository:

```
sbt docker:publishLocal
docker run --detach --name simple-http-server -p 8080:8080 simple-http-server:0.1.0
```

Once the app is up and running, you can ping it to make sure it's running:

```
curl http://localhost:8080/api/v1/healthCheck

OK
```
