package com.crm.Module;

import java.util.List;
import com.crm.Entity.Message;
import com.crm.Entity.User;

/**
 * MessageOperaion
 */
public abstract class MessageOperaion<T, S> implements IMessageOperation<T, S> {
    protected List<IMeesageDelivery> meesageDelivery = null;

    // public void save(User user, Message message) throws Exception {

    // }

    // public void delete(S message) throws Exception {

    // }

    // public void update(S message) throws Exception {

    // }

    // public void status(T user, S message) throws Exception {

    // }

    @Override
    public void addNewMessage(IMeesageDelivery meesageDelivery) {
        if (!this.meesageDelivery.contains(meesageDelivery)) {
            this.meesageDelivery.add(meesageDelivery);
        }
    }

    @Override
    public void sendNotification(T user){
        for (IMeesageDelivery messDelivery : meesageDelivery) {
            messDelivery.notices("test");
        }
    }

}
