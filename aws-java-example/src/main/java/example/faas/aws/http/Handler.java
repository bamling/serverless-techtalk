package example.faas.aws.http;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Handler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    @Override
    public ApiGatewayResponse handleRequest(final Map<String, Object> input, final Context context) {
        final LambdaLogger logger = context.getLogger();

        try {
            // extract body from input
            final JsonNode body = new ObjectMapper().readTree((String) input.get("body"));
            logger.log("Hello " + body.get("name").asText());

            final Response responseBody = new Response("this is the response message", input);
            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(responseBody)
                    .build();

        } catch (final Exception e) {
            logger.log("Got exception: " + e.getMessage());

            final Response responseBody = new Response("something bad happened", input);
            return ApiGatewayResponse.builder()
                    .setStatusCode(500)
                    .setObjectBody(responseBody)
                    .build();
        }
    }
}
