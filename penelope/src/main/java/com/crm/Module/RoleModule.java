package com.crm.Module;

import com.crm.Entity.Role;
import com.crm.Module.Dao.IModule;
import com.crm.Module.Dao.Module;

public class RoleModule extends Module<Role> implements IModule<Role> {
    public RoleModule() {
        super(Role.class);
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

    public Boolean insertData(Role data) throws Exception {
        return (super.insertData(data));
    }

    public Boolean updateData(Role data) throws Exception {
        return (super.updateData(data));
    }

    public Boolean deleteData(Role data) throws Exception {
        return (super.deleteData(data));
    }
}
