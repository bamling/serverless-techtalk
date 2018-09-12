package com.example.faas.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.faas.aws.repository.Note;
import com.example.faas.aws.repository.NotesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class GetNoteHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private static final Logger LOG = LogManager.getLogger(GetNoteHandler.class);

    private NotesRepository notesRepository = new NotesRepository();

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        LOG.info("received: {}", input);

        // get path parameters from input
        final Map<String, String> pathParameters = (Map<String, String>) input.get("pathParameters");
        final String noteId = pathParameters.get("id");

        final Note note = notesRepository.get(noteId);
        if (note != null) {
            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(note)
                    .build();
        }

        return ApiGatewayResponse.builder()
                .setStatusCode(404)
                .build();
    }
}
