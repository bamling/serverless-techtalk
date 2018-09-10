package example.faas.aws.exception;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;

public class Handler implements RequestHandler<Map<String, Object>, String> {

    @Override
    public String handleRequest(final Map<String, Object> input, final Context context) {
        throw new RuntimeException("something bad happened");
    }

}
