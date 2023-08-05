package dev.jaczerob.madamchuckle.launcher.websocket;

import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerWebSocket("/ws/toontown")
public class WebSocketServer {
    private static final Logger LOG = LoggerFactory.getLogger(WebSocketServer.class);

    @OnOpen
    public Publisher<String> onOpen(final WebSocketSession session) {
        LOG.info("User connected");
        return session.send("Hey");
    }

    @OnMessage
    public Publisher<String> onMessage(final String message, final WebSocketSession session) {
        LOG.info("onMessage: {}", message);
        return session.send("Welcome to TTR");
    }

    @OnClose
    public Publisher<String> onClose(final WebSocketSession session) {
        LOG.info("onClose");
        return session.send("Goodbye");
    }
}
