package com.crm.Module.Dao;

import com.crm.Database.MySql;
import com.crm.Tools.Constant;

import java.util.List;

public abstract class Module<T> implements IModule<T> {
    private Class<T> myClass;
    private Boolean state;

    public Module(Class<T> myClass) {
        this.myClass = myClass;
    }

    public Boolean run() throws Exception {
        if (this.myClass == null) {
            throw new Exception("Error Module: Run(): Need to instance module with class<Module>");
        }
        state = Constant.RUN_MODULE;
        return (state);
    }

    public Boolean delete() throws Exception {
        return (true);
    }

    public Boolean edit() throws Exception {
        return (true);
    }

    public Boolean stop() throws Exception {
        state = Constant.STOP_MODULE;
        return (state);
    }

    public Boolean insertData(T data) throws Exception {
        return (MySql.insert(data));
    }

    public Boolean updateData(T data) throws Exception {
        return (MySql.update(data));
    }

    public Boolean deleteData(T data) throws Exception {
        return (MySql.delete(data));
    }

    public List<T> findAll(T data) throws Exception {
        return (MySql.findAll(data));
    }

    public List<T> findByAttr(T data, String attr) throws Exception {
        return (MySql.findByAttr(data, attr));
    }

}
