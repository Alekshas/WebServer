package resourceServer;

public class ResourceServer implements ResourceServerControllerMBean {
    private final TestResource testResource;

    public ResourceServer(TestResource testResource) {
        this.testResource = testResource;
    }

    @Override
    public int getAge() {
    return testResource.getAge();
    }

    @Override
    public String getName() {
    return testResource.getName();
    }
}
