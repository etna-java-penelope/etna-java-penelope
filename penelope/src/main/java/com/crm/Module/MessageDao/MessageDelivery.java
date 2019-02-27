package com.crm.Module.MessageDao;

import com.crm.Entity.User;

/**
 * MessageDelivery
 */
public class MessageDelivery implements IMessageDelivery {

    private User user = new User();

    public MessageDelivery(User newUser){
        this.user = newUser;
    }

    @Override
    public void notices(String message) {
        // Send Email to user
        if (sendMail(message, "Vous avez un mail", user.getEmail())){
            System.out.println("Envoyé");
        }else{
            System.out.println("N'a pas envoyé");
        }

        System.out.println(message + "to" + user);
    }

    private boolean sendMail(String content, String title, String mail) {
        try {
            System.out.println("Mail send to" + mail + "message:" + content + "subject:" + title);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
