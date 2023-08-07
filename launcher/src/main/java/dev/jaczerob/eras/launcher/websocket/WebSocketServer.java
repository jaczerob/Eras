package dev.jaczerob.eras.launcher.websocket;

import dev.jaczerob.eras.launcher.launcher.LauncherService;
import dev.jaczerob.eras.launcher.login.LoginService;
import dev.jaczerob.eras.launcher.login.responses.SuccessfulLoginResponse;
import dev.jaczerob.eras.launcher.patcher.PatcherService;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.Optional;

@Log4j2
@ServerWebSocket("/ws/toontown")
public class WebSocketServer {
    private static final Logger LOG = LoggerFactory.getLogger(WebSocketServer.class);

    private final PatcherService patcherService;
    private final LoginService loginService;
    private final LauncherService launcherService;

    public WebSocketServer(final PatcherService patcherService, final LoginService loginService, final LauncherService launcherService) {
        this.patcherService = patcherService;
        this.loginService = loginService;
        this.launcherService = launcherService;
    }

    @OnOpen
    public Publisher<String> onOpen(final WebSocketSession session) {
        LOG.info("User connected");
        return session.send("Hey");
    }

    @OnMessage
    public Publisher<String> onMessage(final String message, final WebSocketSession session) {
        LOG.info("onMessage: {}", message);


        final byte[] decodedBytes = Base64.getDecoder().decode(message);
        final String decodedString = new String(decodedBytes);

        final String[] credentials = decodedString.split(":");
        final String username = credentials[0];
        final String password = credentials[1];

        LOG.info("Logging in username: {}", username);

        final Optional<SuccessfulLoginResponse> optionalSuccessfulLoginResponse = this.loginService.login(username, password);
        if (optionalSuccessfulLoginResponse.isEmpty()) {
            LOG.error("Login failed");
            return session.send("Login failed");
        }

        LOG.info("Login successful, launching game");
        final SuccessfulLoginResponse successfulLoginResponse = optionalSuccessfulLoginResponse.get();

        final boolean didLaunch = this.launcherService.launch(successfulLoginResponse.getGameserver(), successfulLoginResponse.getCookie());
        if (!didLaunch) {
            LOG.error("Could not launch game");
            return session.send("Could not launch game");
        }

        LOG.info("Game launched");
        return session.send("Login successful");

//        LOG.info("Patching!");
//        this.patcherService.patch();
//        LOG.info("Patched!");
    }

    @OnClose
    public Publisher<String> onClose(final WebSocketSession session) {
        LOG.info("onClose");
        return session.send("Goodbye");
    }
}
