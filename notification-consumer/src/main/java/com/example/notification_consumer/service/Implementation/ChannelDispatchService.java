package com.example.notification_consumer.service.Implementation;

import com.example.notification_consumer.exception.NotFoundException;
import com.example.notification_consumer.model.ChannelHandlerContext;
import com.example.notification_consumer.service.ChannelHandler;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChannelDispatchService {
    private final Map<String, ChannelHandler> handlers;

    public ChannelDispatchService(Map<String, ChannelHandler> handlers) {
        this.handlers = handlers;
    }

    public void dispatch(ChannelHandlerContext ctx) throws Exception {
        String name = ctx.getChannel().getName().toUpperCase();
        ChannelHandler h = handlers.get(name);
        if (h == null) throw new NotFoundException("No handler found for " + name);
        h.handle(ctx.getUser(), ctx.getType(), ctx.getTemplate(), ctx.getContent());
    }
}
