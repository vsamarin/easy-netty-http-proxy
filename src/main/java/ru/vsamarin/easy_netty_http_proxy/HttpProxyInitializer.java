package ru.vsamarin.easy_netty_http_proxy;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class HttpProxyInitializer extends ChannelInitializer<SocketChannel> {

    private final String remoteHost;
    private final int remotePort;

    public HttpProxyInitializer(String remoteHost, int remotePort) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ch.pipeline().addLast(
                new LoggingHandler(LogLevel.INFO),
                new HttpProxyFrontendHandler(remoteHost, remotePort));
    }
}