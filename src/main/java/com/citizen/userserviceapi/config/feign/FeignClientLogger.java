package com.citizen.userserviceapi.config.feign;

import feign.Request;
import feign.Response;
import feign.Util;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class FeignClientLogger extends feign.Logger {

    @Override
    protected void log(String s, String s1, Object... objects) {
        log.info(String.format(methodTag(s) + s1, objects));
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        log.info("Request to '{}'\nMethod: '{}'\nHeaders: {}", request.url(), request.httpMethod(), request.headers());
        if (request.body() != null) {
            log.info("Body: {}", Util.decodeOrDefault(request.body(), StandardCharsets.UTF_8, "Binary data"));
        }
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
        if (response.body() == null) {
            log.info("Response from '{}'\nMethod: '{}'\nStatus: {} {}\nHeaders: '{}'\nBody is null",
                    response.request().url(), response.request().httpMethod(), response.status(), response.reason(), response.headers());
        }

        byte[] body = Util.toByteArray(response.body().asInputStream());
        log.info("Response from '{}'\nMethod: '{}'\nStatus: {} {}\nHeaders: '{}'\nBody: '{}'",
                response.request().url(), response.request().httpMethod(), response.status(), response.reason(), response.headers(), body);

        return response.toBuilder()
                .body(body)
                .build();
    }

    @Override
    protected IOException logIOException(String configKey, Level logLevel, IOException ioe, long elapsedTime) {
        log.error("IOException on feign client!", ioe);
        return ioe;
    }
}
