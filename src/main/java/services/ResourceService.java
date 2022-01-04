package services;

import resourceServer.TestResource;

public class ResourceService {
    private TestResource testResource;

    public ResourceService(TestResource testResource) {
        this.testResource = testResource;
    }
    public String getName(){return testResource.getName();};

    public int getAge(){return  testResource.getAge();};

    public void setTestResource(TestResource testResource) {
        this.testResource = testResource;
    }
}
