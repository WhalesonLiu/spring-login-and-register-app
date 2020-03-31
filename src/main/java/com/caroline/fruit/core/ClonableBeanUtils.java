package com.caroline.fruit.core;

import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class ClonableBeanUtils {
    /**
     * use reflect
     */
    public static void copyPropertiesByReflect(Object source, Object target) throws RuntimeException, IllegalAccessException, InstantiationException {

        if (source == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (target == null) {
            throw new NullPointerException("Target must not be null");
        }


        Class<?> sourceCls = source.getClass();
        Class<?> targetCls = target.getClass();
        for (Field field : sourceCls.getDeclaredFields()) {
            field.setAccessible(true);
            //Judge whether it is a class created by JDK
            if (field.getType().getClassLoader() != null) {
                Object subSource = field.get(source);
                String subFieldName = field.getName();
                try {
                    Field targetObjectField = targetCls.getDeclaredField(subFieldName);
                    targetObjectField.setAccessible(true);
                    Object subTarget =targetObjectField.get(target);
                    if(subTarget == null){
                        subTarget = targetObjectField.getType().newInstance();
                    }
                    copyPropertiesByReflect(subSource,subTarget);
                    //set sub element
                    targetObjectField.set(target,subTarget);

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }else{
                String sourceFieldName = field.getName();
                try {
                    Object sourceFieldValue = field.get(source);


                    //set value
                    Field targetField = getField(targetCls, sourceFieldName);

                    if (targetField != null) {

                        targetField.setAccessible(true);
                        targetField.set(target, sourceFieldValue);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private static Field getField(Class clazz, String fieldName) {
        try {

            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
        }
        return null;
    }

    /**
     * use introspector
     */
    public static void copyPropertiesByIntrospector(Object source, Object target) throws RuntimeException {
        if (source == null) {
            throw new NullPointerException("Source must not be null");
        }
        try {
            BeanInfo targetBeanInfo = Introspector.getBeanInfo(target.getClass());

            PropertyDescriptor [] targetPropertyDescriptors= targetBeanInfo.getPropertyDescriptors();
            if(targetPropertyDescriptors.length <= 0){
                throw new RuntimeException("target has no property");
            }
            for (PropertyDescriptor targetPropertyDescriptor : targetPropertyDescriptors) {
                Class targetFieldType = targetPropertyDescriptor.getPropertyType();
                if( targetFieldType.getClassLoader() == null){
                    Method targetMethod = targetPropertyDescriptor.getWriteMethod();
                    String targetFieldName = targetPropertyDescriptor.getName();


                    PropertyDescriptor sourcePropertyDescriptor =BeanUtils.getPropertyDescriptor(source.getClass(),targetFieldName);
                    if(sourcePropertyDescriptor != null && !"class".equals(sourcePropertyDescriptor.getName())){
                        Method sourceMethod = sourcePropertyDescriptor.getReadMethod();
                        Object sourceValue = sourceMethod.invoke(source);
                        targetMethod.invoke(target,sourceValue);
                    }
                }else {
                    //targetPropertyDescriptor

                    PropertyDescriptor subSourcePropertyDescriptor =BeanUtils.getPropertyDescriptor(source.getClass(),targetPropertyDescriptor.getName());
                    Method subSourceMethod = subSourcePropertyDescriptor.getReadMethod();
                    Object subSourceObject = subSourceMethod.invoke(source);

                    Method subTargetMethod = targetPropertyDescriptor.getReadMethod();
                    Object subTargetObject = subTargetMethod.invoke(target);
                    if(subTargetObject == null){
                        subTargetObject = targetPropertyDescriptor.getPropertyType().newInstance();
                    }
                    Method subTargetWriteMethod = targetPropertyDescriptor.getWriteMethod();
                    copyPropertiesByIntrospector(subSourceObject,subTargetObject);
                    subTargetWriteMethod.invoke(target,subTargetObject);
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        source.getClass();

    }
}
