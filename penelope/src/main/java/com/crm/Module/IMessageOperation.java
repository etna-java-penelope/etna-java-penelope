package com.crm.Module;

import com.crm.Entity.Message;
import com.crm.Entity.User;

/**
 * IMessageOperation
 */
public interface IMessageOperation<T,S> {

    // public void save(T user, S message) throws Exception;

    // public void delete(S message) throws Exception;

    // public void update(S message) throws Exception;

    // public void status(T user, S message) throws Exception;

    public void addNewMessage(IMeesageDelivery meesageDelivery);

    public void sendNotification(T user);

}
