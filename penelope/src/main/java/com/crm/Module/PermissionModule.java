package com.crm.Module;

import com.crm.Entity.Permission;
import com.crm.Module.Dao.IModule;
import com.crm.Module.Dao.Module;

public class PermissionModule extends Module<Permission> implements IModule<Permission> {
    public PermissionModule() {
        super(Permission.class);
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

    public Boolean insertData(Permission data) throws Exception {
        return (super.insertData(data));
    }

    public Boolean updateData(Permission data) throws Exception {
        return (super.updateData(data));
    }

    public Boolean deleteData(Permission data) throws Exception {
        return (super.deleteData(data));
    }
}
