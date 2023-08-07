package dev.jaczerob.madamchuckle.launcher.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jaczerob.madamchuckle.launcher.login.requests.InitialLoginRequest;
import dev.jaczerob.madamchuckle.launcher.login.requests.LoginRequest;
import dev.jaczerob.madamchuckle.launcher.login.requests.QueuedLoginRequest;
import dev.jaczerob.madamchuckle.launcher.login.responses.LoginResponse;
import dev.jaczerob.madamchuckle.launcher.login.responses.SuccessfulLoginResponse;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.uri.UriBuilder;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@Singleton
public class LoginService {
    private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);
    private static final String LOGIN_URL_BASE = "https://toontownrewritten.com/api/login";

    private final URI loginURI;
    private final ObjectMapper objectMapper;
    private final BlockingHttpClient httpClient;

    public LoginService(final HttpClient httpClient, final ObjectMapper objectMapper) {
        this.loginURI = UriBuilder.of(LOGIN_URL_BASE)
                .queryParam("format", "json")
                .build();

        this.httpClient = httpClient.toBlocking();
        this.objectMapper = objectMapper;
    }

    public Optional<SuccessfulLoginResponse> login(final String username, final String password) {
        Optional<LoginResponse> optionalLoginResponse = this.tryLogin(username, password);
        if (optionalLoginResponse.isEmpty()) {
            LOG.error("Could not get login response");
            return Optional.empty();
        }

        LoginResponse loginResponse = optionalLoginResponse.get();

        while (!loginResponse.getSuccess().equals("true")) {
            switch (loginResponse.getSuccess()) {
                case "false", "partial" -> {
                    LOG.error("Login failed: {}", loginResponse.getSuccess());
                    LOG.error("Reason: {}", loginResponse.getBanner());
                    return Optional.empty();
                }
                case "delayed" -> {
                    LOG.info("Login delayed");

                    optionalLoginResponse = this.tryLogin(loginResponse.getQueueToken());
                    if (optionalLoginResponse.isEmpty()) {
                        LOG.error("Could not get queued login response");
                        return Optional.empty();
                    }

                    loginResponse = optionalLoginResponse.get();
                }
                default -> {
                    LOG.error("Unknown login response: {}", loginResponse.getSuccess());
                    return Optional.empty();
                }
            }
        }

        return Optional.of(new SuccessfulLoginResponse(loginResponse.getGameserver(), loginResponse.getCookie()));
    }

    private Optional<LoginResponse> tryLogin(final LoginRequest loginRequest) {
        final String loginRequestJson;
        try {
            loginRequestJson = this.objectMapper.writeValueAsString(loginRequest);
        } catch (final IOException exc) {
            LOG.error("Error serializing login request: {}", exc.getMessage());
            return Optional.empty();
        }

        final HttpRequest<String> request = HttpRequest.POST(this.loginURI, loginRequestJson);

        final String response = this.httpClient.retrieve(request, String.class);

        if (response == null) {
            LOG.error("No response from server");
            return Optional.empty();
        }

        final LoginResponse loginResponse;
        try {
            loginResponse = this.objectMapper.readValue(response, LoginResponse.class);
        } catch (final IOException exc) {
            LOG.error("Error parsing login response: {}", exc.getMessage());
            return Optional.empty();
        }

        return Optional.of(loginResponse);
    }

    private Optional<LoginResponse> tryLogin(final String queueToken) {
        return this.tryLogin(new QueuedLoginRequest(queueToken));
    }

    private Optional<LoginResponse> tryLogin(final String username, final String password) {
        return this.tryLogin(new InitialLoginRequest(username, password));
    }
}
