package com.sviatoslavHavrylo.tests;

import com.sviatoslavHavrylo.taskSolution.BackendInstance;
import com.sviatoslavHavrylo.taskSolution.LoadBalancer;
import com.sviatoslavHavrylo.taskSolution.exception.LoadBalancerException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadBalancerTest {

    private LoadBalancer loadBalancer;

    private int maxSize = LoadBalancer.getMaxAcceptInstances();

    @Before
    public void setUp() throws Exception {
        loadBalancer = new LoadBalancer();
    }

    @After
    public void tearDown() throws Exception {
        loadBalancer = null;
    }

    @Test
    @DisplayName("Reached max size")
    public void reachedMaxSize() {
        populateLoadBalancer();
        assertEquals(LoadBalancer.getMaxAcceptInstances(), loadBalancer.getBackendInstances().size(), "Set size should reach the maximum size");
    }

    @Test
    @DisplayName("Populate not unique address")
    public void uniqueInstances() {
        for (int i = 0; i < maxSize; i++) {
            addInstance();
        }
        assertEquals(1, loadBalancer.getBackendInstances().size(), "Only unique address should be");
    }

    @Test(expected = LoadBalancerException.class)
    @DisplayName("Catch max size exception")
    public void addBackendInstance() {
        populateLoadBalancer();
        addInstance();
    }

    private void addInstance() {
        loadBalancer.addBackendInstance(new BackendInstance("backendInstance address"));
    }

    private void populateLoadBalancer() {
        for (int i = 0; i < maxSize; i++) {
            loadBalancer.addBackendInstance(new BackendInstance("backendInstance " + i));
        }
    }
}