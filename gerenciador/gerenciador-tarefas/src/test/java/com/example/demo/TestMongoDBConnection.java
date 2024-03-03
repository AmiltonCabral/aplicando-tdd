package com.example.demo;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;


public class TestMongoDBConnection {
    static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    @Test
    public void testConnectionContainerMongo(){
        mongoDBContainer.start();
        Assertions.assertTrue(mongoDBContainer.isCreated());
        Assertions.assertTrue(mongoDBContainer.isRunning());
    }

     @Test
    public void testCloseConnectionContainerMongo(){
        mongoDBContainer.stop();
        Assertions.assertFalse(mongoDBContainer.isRunning());
    }
}
