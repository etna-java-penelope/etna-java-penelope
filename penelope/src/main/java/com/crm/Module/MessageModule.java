package com.crm.Module;

import com.crm.Entity.User;
import com.crm.Module.Dao.IModule;
import com.crm.Module.Dao.Module;
import java.util.List;

/**
 * MessageMOdule
 */
public class MessageModule extends Module<User> implements IModule<User> {
    public MessageModule() {
        super(User.class);
    }

    public Boolean run() throws Exception {
        return (super.run());
    }

    public Boolean stop() throws Exception {
        return (super.stop());
    }

    public Boolean delete() throws Exception {
        return (super.delete());
    }

    public Boolean edit() throws Exception {
        return (super.edit());
    }

    public Boolean insertData(User data) throws Exception {
        return (super.insertData(data));
    }

    public Boolean updateData(User data) throws Exception {
        return (super.updateData(data));
    }

    public Boolean deleteData(User data) throws Exception {
        return (super.deleteData(data));
    }

    public List<User> findAll(User data) throws Exception {
        return (super.findAll(data));
    }

    public List<User> findByAttr(User data, String attr) throws Exception {
        return (super.findByAttr(data, attr));
    }

}
