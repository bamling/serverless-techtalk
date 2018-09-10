package example.faas.aws.greeter;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<GreeterRequest, GreeterResponse> {

    @Override
    public GreeterResponse handleRequest(final GreeterRequest input, final Context context) {
        final LambdaLogger logger = context.getLogger();
        logger.log("function name: " + context.getFunctionName() + "\n");
        logger.log("received firstName: " + input.getFirstName() + " and lastName: " + input.getLastName() + "\n");

        return new GreeterResponse("Hello " + input.getFirstName() + " " + input.getLastName());
    }
}
