package example.faas.aws.greeter;

public class GreeterResponse {

    private String greeting;

    public GreeterResponse() {

    }

    public GreeterResponse(final String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
