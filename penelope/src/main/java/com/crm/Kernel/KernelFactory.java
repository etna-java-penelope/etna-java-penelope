package com.crm.Kernel;

import com.crm.Module.Dao.Module;
import com.crm.Tools.Constant;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.util.Collection;

public abstract class KernelFactory
{
    public static Module getModule(String module) throws ClassNotFoundException
    {
        for (Object tmp : listModule()) {
            try {
                String filenameWithExt = tmp.toString().split("/")[1];
                String filenameNoExt = tmp.toString().split("/")[1].split("\\.")[0];
                if (filenameWithExt.contains(module)) {
                    Class<?> m = Class.forName("Module." + filenameNoExt);
                    Object insModule = m.newInstance();
                    return ((Module<?>) insModule);
                }
            } catch (Exception e) {
                throw new ClassNotFoundException("Error syntax module");
            }
        }
        return (null);
    }

    public static Collection listModule()
    {
        File directory = new File(Constant.DIRECTORY_MODULE);
        return (FileUtils.listFiles(directory, new WildcardFileFilter(Constant.EXTENSION_JAVA), null));
    }
}
