package ru.vsamarin.easy_netty_http_proxy.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // Discard the received data silently.
        //((ByteBuf) msg).release();

        /*
        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) {
                System.out.println(in.readByte());
                System.out.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg);
        } */

        ByteBuf in = (ByteBuf) msg;
        try {
            System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));
            //System.out.flush();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

}
