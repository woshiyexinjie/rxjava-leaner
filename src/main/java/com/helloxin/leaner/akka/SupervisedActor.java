package com.helloxin.leaner.akka;

import akka.actor.AbstractActor;

/**
 * Created by nandiexin on 2018/8/21.
 */
class SupervisedActor extends AbstractActor {
    @Override
    public void preStart() {
        System.out.println("supervised actor started");
    }

    @Override
    public void postStop() {
        System.out.println("supervised actor stopped");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("fail", f -> {
                    System.out.println("supervised actor fails now");
                    throw new Exception("I failed!");
                })
                .build();
    }
}
