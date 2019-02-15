package com.crm.Module.Dao;

import java.util.List;

public interface IModule<T> {
    /**
     * This method is used to Run Module when is ready
     *
     * @return state method (true/false) (success/failure)
     */
    Boolean run() throws Exception;

    /**
     * This method is used to Stop execution of module instance
     *
     * @return state method (true/false) (success/failure)
     */
    Boolean stop() throws Exception;

    /**
     * This method is used to Delete Module from kernel, be careful ...
     *
     * @return state method (true/false) (success/failure)
     */
    Boolean delete() throws Exception;

    /**
     * This method is used to Edit Name module
     *
     * @return state method (true/false) (success/failure)
     */
    Boolean edit() throws Exception;

    /**
     * This method is used to insert data to module storage (like mysql)
     *
     * @param data T => entity
     * @return data entity
     */
    Boolean insertData(T data) throws Exception;

    /**
     * This method is used to update data to module storage (like mysql)
     *
     * @param data T => entity
     * @return data entity
     */
    Boolean updateData(T data) throws Exception;

    /**
     * This method is used to delete data to module storage (like mysql)
     *
     * @param data T => entity
     * @return data entity
     */
    Boolean deleteData(T data) throws Exception;

    /**
     * This method is used to get all entity in db (like mysql)
     *
     * @param data T => entity
     * @return data entity
     */
    List<T> findAll(T data) throws Exception;

    /**
     * This method is used to get entity by id in db (like mysql)
     *
     * @param data, attr = column except, like email, id, lastname ..
     * @return data entity
     */
    List<T> findByAttr(T data, String attr) throws Exception;
}
