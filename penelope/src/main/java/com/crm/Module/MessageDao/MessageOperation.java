package com.crm.Module.MessageDao;

import java.util.List;

/**
 * MessageOperaion
 */
public abstract class MessageOperation<User> implements IMessageOperation<User> {
    protected List<IMessageDelivery> messageDelivery = null;

    // public void doNotification(T user, S message) throws Exception {
    // }

    @Override
    public void addNewMessage(IMessageDelivery meesageDelivery) {
        if (!this.messageDelivery.contains(meesageDelivery)) {
            this.messageDelivery.add(meesageDelivery);
        }
    }

    @Override
    public void sendNotification(User user){
        for (IMessageDelivery messDelivery : messageDelivery) {
            messDelivery.notices("test");
        }
    }

}
