package com.newcodebbs.controller;

import org.springframework.stereotype.Component;

import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint(value = "/connectWebSocket/{userId}")
public class WebSocketController {
}
