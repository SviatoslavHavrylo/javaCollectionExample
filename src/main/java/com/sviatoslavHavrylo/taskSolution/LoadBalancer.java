package com.sviatoslavHavrylo.taskSolution;

import com.sviatoslavHavrylo.taskSolution.exception.LoadBalancerException;

import java.util.HashSet;
import java.util.Set;

public class LoadBalancer {

    private static final int MAX_ACCEPT_INSTANCES = 10;

    private Set<BackendInstance> backendInstances;

    public static int getMaxAcceptInstances() {
        return MAX_ACCEPT_INSTANCES;
    }

    public Set<BackendInstance> getBackendInstances() {
        return backendInstances;
    }

    public void setBackendInstances(Set<BackendInstance> backendInstances) {
        this.backendInstances = backendInstances;
    }

    public LoadBalancer() {
        backendInstances = new HashSet<BackendInstance>();
    }

    public void addBackendInstance(BackendInstance backendInstance) {
        if (backendInstances.size() >= MAX_ACCEPT_INSTANCES) {
            throw new LoadBalancerException("Can not add instance, max size is 10");
        }

            backendInstances.add(backendInstance);
    }
}
