package com.example.demo.config;

import com.example.demo.aop.SimpleAutowired;
import com.example.demo.aop.SimpleBean;
import com.example.demo.aop.SimpleConfiguration;
import com.example.demo.aop.SimpleController;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SimpleApplicationContext {
    private final Map<String,Object> beanMap=new HashMap<>();
    public SimpleApplicationContext(Class<?> configClass){
        try{
            //扫描配置类并注册@SimpleBean
            scanConfig(configClass);
            //扫描配置类同级包下的controller（为了简化，其实可以配置）
            scanController("com.example.demo.handler");
            //执行依赖注入（DI）
            doInjection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void doInjection() throws Exception {
        for (Object bean : beanMap.values()) {
            // 遍历所有字段
            for (Field field : bean.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(SimpleAutowired.class)) {
                    field.setAccessible(true);
                    // 根据字段类型找 Bean (简化版：通过类型匹配)
                    Object dependency = findBeanByType(field.getType());

                    if (dependency != null) {
                        field.set(bean, dependency);
                        System.out.println("IOC容器 注入依赖: " + bean.getClass().getSimpleName() + "." + field.getName());
                    }
                }
            }
        }
    }

    private void scanController(String packageName) throws Exception{
        URL url = this.getClass().getClassLoader().getResource(packageName.replace(".", "/"));
        if (url == null) return;

        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                // 递归扫描子包
                scanController(packageName + "." + file.getName());
            } else {
                String className = packageName + "." + file.getName().replace(".class", "");
                Class<?> clazz = Class.forName(className);

                if (clazz.isAnnotationPresent(SimpleController.class)) {
                    Object instance = clazz.newInstance();
                    // 简单起见，类名首字母小写作为 beanName
                    String beanName = lowerFirst(clazz.getSimpleName());
                    beanMap.put(beanName, instance);
                    System.out.println("IOC容器 注册 Controller: " + beanName);
                }
            }
        }
    }

    private void scanConfig(Class<?> configClass) throws Exception{
        if (!configClass.isAnnotationPresent(SimpleConfiguration.class)) {
            return;
        }

        // 实例化配置类自身 (比如 MpConfig)
        Object configInstance = configClass.newInstance();

        // 遍历所有方法，找到 @SimpleBean
        for (Method method : configClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(SimpleBean.class)) {
                // 执行方法，获取返回值 (例如拿到 UserMapper 的代理对象)
                Object beanInstance = method.invoke(configInstance);

                // 默认用方法名作为 beanName (比如 "userMapper")
                beanMap.put(method.getName(), beanInstance);
                System.out.println("IOC容器 注册 Bean: " + method.getName());
            }
        }
    }

    private Object findBeanByType(Class<?> type) {
        for (Object bean : beanMap.values()) {
            // 如果容器里的 bean 是 type 的实例 (或者是它的子类/代理类)
            if (type.isAssignableFrom(bean.getClass())) {
                return bean;
            }
        }
        return null;
    }

    // 获取 Bean 的方法供外部调用
    public Object getBean(String name) {
        return beanMap.get(name);
    }

    private String lowerFirst(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

}
