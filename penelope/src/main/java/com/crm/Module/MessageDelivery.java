package com.crm.Module;

import com.crm.Entity.User;

/**
 * MessageDelivery
 */
public class MessageDelivery implements IMeesageDelivery {

    private User user = null;

    @Override
    public void notices(String message) {
        // Send Email to user
        System.out.println(message);
    }
}
