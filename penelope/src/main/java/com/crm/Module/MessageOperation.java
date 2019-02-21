package com.crm.Module;

import java.util.List;

/**
 * MessageOperaion
 */
public abstract class MessageOperation<T, S> implements IMessageOperation<T, S> {
    protected List<IMessageDelivery> messageDelivery = null;

    // public void save(User user, Message message) throws Exception {

    // }

    // public void delete(S message) throws Exception {

    // }

    // public void update(S message) throws Exception {

    // }

    // public void status(T user, S message) throws Exception {

    // }

    @Override
    public void addNewMessage(IMessageDelivery meesageDelivery) {
        if (!this.messageDelivery.contains(meesageDelivery)) {
            this.messageDelivery.add(meesageDelivery);
        }
    }

    @Override
    public void sendNotification(T user){
        for (IMessageDelivery messDelivery : messageDelivery) {
            messDelivery.notices("test");
        }
    }

}
