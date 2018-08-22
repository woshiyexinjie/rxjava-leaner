package com.helloxin.leaner.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created by nandiexin on 2018/8/21.
 */
class StartStopActor1 extends AbstractActor {
    @Override
    public void preStart() {
        System.out.println("first started");
        getContext().actorOf(Props.create(StartStopActor2.class), "second");
    }

    @Override
    public void postStop() {
        System.out.println("first stopped");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("stop", s -> {
                    getContext().stop(getSelf());
                })
                .build();
    }

    public static void main(String[] args) {

        ActorSystem system = ActorSystem.create("testSystem");
        ActorRef first = system.actorOf(Props.create(StartStopActor1.class), "first");
        first.tell("stop", ActorRef.noSender());
    }
}

