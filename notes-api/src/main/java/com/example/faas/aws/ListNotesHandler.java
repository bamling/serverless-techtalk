package com.example.faas.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.faas.aws.repository.NotesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class ListNotesHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private static final Logger LOG = LogManager.getLogger(ListNotesHandler.class);

    private NotesRepository notesRepository = new NotesRepository();

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        LOG.info("received: {}", input);

        return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(notesRepository.list())
                .build();
    }
}
