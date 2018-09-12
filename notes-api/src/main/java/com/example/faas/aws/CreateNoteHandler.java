package com.example.faas.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.faas.aws.repository.Note;
import com.example.faas.aws.repository.NotesRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Map;

public class CreateNoteHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private static final Logger LOG = LogManager.getLogger(CreateNoteHandler.class);

    private NotesRepository notesRepository = new NotesRepository();

    @Override
    public ApiGatewayResponse handleRequest(final Map<String, Object> input, final Context context) {
        LOG.info("received: {}", input);

        try {
            // extract body from input
            final JsonNode body = new ObjectMapper().readTree((String) input.get("body"));

            // create the Note object
            final Note note = new Note();
            note.setAuthor(body.get("author").asText());
            note.setText(body.get("text").asText());
            notesRepository.save(note);

            return ApiGatewayResponse.builder()
                    .setStatusCode(201)
                    .setObjectBody(note)
                    .build();
        } catch (final IOException e) {
            final Response response = new Response("error while saving note", input);
            return ApiGatewayResponse.builder()
                    .setStatusCode(500)
                    .setObjectBody(response)
                    .build();
        }
    }
}
