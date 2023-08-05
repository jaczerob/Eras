package dev.jaczerob.madamchuckle.launcher.websocket;

import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerWebSocket("/ws/toontown/{username}")
public class WebSocketServer {
    private static final Logger LOG = LoggerFactory.getLogger(WebSocketServer.class);

    @OnOpen
    public Publisher<String> onOpen(final String username, final WebSocketSession session) {
        LOG.info("User {} connected", username);
        return session.send("Hey, %s".formatted(username));
    }

    @OnMessage
    public Publisher<String> onMessage(
            final String username,
            final String message,
            final WebSocketSession session
    ) {
        LOG.info("onMessage: {} from {}", message, username);
        return session.send("Welcome to TTR, %s".formatted(username));
    }

    @OnClose
    public Publisher<String> onClose(
            final String username,
            final WebSocketSession session
    ) {
        LOG.info("onClose from {}", username);
        return session.send("Goodbye, %s".formatted(username));
    }
}
