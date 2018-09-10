package example.faas.aws.greeter;

public class GreeterRequest {

    private String firstName;
    private String lastName;

    public GreeterRequest() {

    }

    public GreeterRequest(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
