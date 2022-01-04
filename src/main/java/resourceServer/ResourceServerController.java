package resourceServer;

public class ResourceServerController implements ResourceServerControllerMBean {
    private final TestResource testResource;

    public ResourceServerController(TestResource testResource) {
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
