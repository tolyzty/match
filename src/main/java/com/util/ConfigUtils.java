package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigUtils {

    @SuppressWarnings("unused")
	private static String     propPath = File.separator + "config.properties";
    private static Properties prop     = null;//公共的配置文件
    private Properties privateProp; //私有的配置 文件
    
    public ConfigUtils(String propPath){
    	privateProp = new Properties();
    	FileInputStream in = null;
		try {
			in = new FileInputStream(ConfigUtils.class.getResource(propPath).getPath());
			privateProp.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    static {
//        prop = new Properties();
//        System.out.println(ConfigUtils.class.getResource(""));  
//        System.out.println(ConfigUtils.class.getResource("/"));  
//        System.out.println(ConfigUtils.class.getClassLoader().getResource(""));  
//        System.out.println(ConfigUtils.class.getClassLoader().getResource("/"));  
//        System.out.println(ConfigUtils.class.getResource("config.properties"));  
//        System.out.println(ConfigUtils.class.getResource("/config.properties"));  
//        System.out.println(ConfigUtils.class.getClassLoader().getResource("config.properties"));
////        ConfigUtils.class.getResource( "config.properties" );    //ClassLoader.getSystemResourceAsStream("/config.properties");
////        try {
//////            prop.load(in);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
    	prop = new Properties();
    	FileInputStream in = null;
		try {
			in = new FileInputStream(ConfigUtils.class.getResource("/config.properties").getPath());
			prop.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 得到私有配置文件属性值
     * @param keyName
     * @return
     */
    public String getPrivateProperty(String keyName){
    	return privateProp.getProperty(keyName);
    }
    /**
     * 得到私有配置文件属性值
     * @param keyName
     * @param defaultValue
     * @return
     */
    public String getPrivateProperty(String keyName, String defaultValue){
    	return privateProp.getProperty(keyName,defaultValue);
    }
    
    /**
     * 获得公共参数值
     * @param keyName
     * @return
     */
    public String getPublicProperty(String keyName){
    	return getProperty(keyName);
    }
    /**
     * 获得公共参数值
     * @param keyName
     * @param defaultValue
     * @return
     */
    public String getPublicProperty(String keyName, String defaultValue){
    	return getProperty(keyName,defaultValue);
    }
    

    public static String getProperty(String keyName) {
        return prop.getProperty(keyName);
    }

    public static String getProperty(String keyName, String defaultValue) {
        return prop.getProperty(keyName, defaultValue);
    }

}
