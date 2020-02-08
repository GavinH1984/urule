package cn.gavinhuang.model;

import com.bstek.urule.ClassUtils;

import java.io.File;

public class ExportModelUtil {
    public static void main(String[] args) {
        File file=new File("d:/customer.xml");
        ClassUtils.classToXml(Customer.class, file);
    }
}
