package org.acme;

import static io.quarkus.scheduler.Scheduled.ConcurrentExecution.SKIP;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.scheduler.Scheduled;

@ApplicationScoped
public class Scheduler {

    @RestClient
    EventService eventSvc;

    @Scheduled(every = "10m", identity = "scheduler-poller-job", concurrentExecution = SKIP)
    void poller() {
        eventSvc.getEvents();
    }
}
