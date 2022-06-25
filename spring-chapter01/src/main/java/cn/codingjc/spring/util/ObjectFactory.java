package cn.codingjc.spring.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 定义一个对象的工厂类
 * @author shenjicheng
 * @date 2022/6/25 3:29 PM
 */
public class ObjectFactory {

    private static final String CONFIG_NAME = "beans.properties";

    // 保存业务接口实例
    private static final Map<String, Object> INSTANCE_POOL_MAP = new ConcurrentHashMap<>();

    /**
     * 加载配置文件
     */
    static {
        // 配置文件路径
        String configPath = Thread.currentThread().getContextClassLoader()
                .getResource("").getPath() + "cn" + File.separator + "codingjc" + File.separator
                + "spring" + File.separator + "config" + File.separator + CONFIG_NAME;
        System.out.println(configPath);
        // 读取配置文件
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(configPath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(properties);
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String beanName = entry.getKey().toString();
            try {
                Class<?> clazz = Class.forName(entry.getValue().toString());
                Object instance = clazz.getConstructor().newInstance();
                INSTANCE_POOL_MAP.put(beanName, instance);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 构造方法私有化
     */
    public ObjectFactory() {}

    /**
     * 指定的类型的类，可以通过此方法获取该类的实例化对象
     * @param className 类名称
     * @param type 类型
     * @return 实例化对象
     * @param <T> 返回的对象类型标记
     */
    public static <T> T getInstance1(String className, Class<T> type){
        Object instance = null;
        try {
            Class<?> clazz = Class.forName(className);
            instance = clazz.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (T) instance;
    }

    public static <T> T getInstance2(String className, Class<T> type){
        return (T) INSTANCE_POOL_MAP.get(className);
    }
}
