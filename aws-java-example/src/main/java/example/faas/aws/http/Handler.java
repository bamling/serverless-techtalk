package example.faas.aws.http;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class Handler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private static final Logger LOG = LogManager.getLogger(Handler.class);

    @Override
    public ApiGatewayResponse handleRequest(final Map<String, Object> input, final Context context) {
        LOG.info("received: {}", input);

        final Response responseBody = new Response("This is the response message", input);
        return ApiGatewayResponse.builder()
                .setStatusCode(400)
                .setObjectBody(responseBody)
                .build();
    }
}
