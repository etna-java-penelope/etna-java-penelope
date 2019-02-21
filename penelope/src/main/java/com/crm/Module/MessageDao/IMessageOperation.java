package com.crm.Module.MessageDao;

/**
 * IMessageOperation
 */
public interface IMessageOperation<T> {

    // public void save(T user, S message) throws Exception;

    // public void delete(S message) throws Exception;

    // public void update(S message) throws Exception;

    // public void status(T user, S message) throws Exception;

    public void addNewMessage(IMessageDelivery messageDelivery);

    public void sendNotification(T user);

}