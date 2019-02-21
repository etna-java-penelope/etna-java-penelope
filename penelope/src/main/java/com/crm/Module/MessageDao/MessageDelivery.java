package com.crm.Module.MessageDao;

import com.crm.Entity.User;

/**
 * MessageDelivery
 */
public class MessageDelivery implements IMessageDelivery {

    private User user = null;

    @Override
    public void notices(String message) {
        // Send Email to user
        System.out.println(message);
    }
}
