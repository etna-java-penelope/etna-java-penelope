package com.crm.Module.MessageDao;

import com.crm.Entity.Message;
import com.crm.Entity.User;

/**
 * IMessageDelivery
 */
public interface IMessageDelivery {

    public void notices(String message);
}
