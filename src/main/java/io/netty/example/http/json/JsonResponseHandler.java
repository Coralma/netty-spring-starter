package io.netty.example.http.json;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.google.gson.Gson;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

/**
 * A simple handler which will simple return a successful Http
 * response for any request.
 */
public class JsonResponseHandler extends SimpleChannelInboundHandler<Object> {

    private String url;

    /*public JsonResponseHandler(String url) {
        this.url = url;
    }*/

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        /*FullHttpRequest request = null;
        String uri = null;
        if(msg instanceof FullHttpRequest) {
            request = (FullHttpRequest) msg;
            uri = request.uri();
            String data = sanitizeUri(uri);
            System.out.println(data);
        }*/
        DataModel dm = new DataModel("Coral", "123");
        Gson gson = new Gson();
        String res = gson.toJson(dm);
        final FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(res.getBytes("UTF-8")));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private String sanitizeUri(String uri) {
        try {
            uri = URLDecoder.decode(uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            try {
                uri = URLDecoder.decode(uri, "ISO-8859-1");
            } catch (UnsupportedEncodingException e1) {
                throw new Error();
            }
        }
        if(!uri.startsWith(url)) {
            return null;
        }
        if(!uri.startsWith("/")) {
            return null;
        }
        return null;
    }
}