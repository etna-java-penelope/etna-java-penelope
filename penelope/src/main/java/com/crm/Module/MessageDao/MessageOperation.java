package com.crm.Module.MessageDao;

import java.util.ArrayList;
import java.util.List;

/**
 * MessageOperaion
 */
public class MessageOperation implements IMessageOperation {
    protected List<IMessageDelivery> messageDelivery = null;

    public MessageOperation() {
        messageDelivery = new ArrayList<IMessageDelivery>();
    }

    @Override
    public void addNewMessage(IMessageDelivery meesageDelivery) {
        if (!this.messageDelivery.contains(meesageDelivery)) {
            this.messageDelivery.add(meesageDelivery);
        }
    }

    @Override
    public void sendNotification(String message){
        for (IMessageDelivery messDelivery : messageDelivery) {
            messDelivery.notices(message);
        }
    }

}
