package ucl.property.info;

import ucl.property.base.UCLPropertyBase;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * ZCPS相关映射及显示
 * Created by seu on 2018/3/29.
 */
public class ZCPSInfo {
    public static String DEFAULT_PATTERN = ";";
    //ZCPS受控映射表
    public Map<Integer, String> spaceLocMap = new HashMap<>();
    public Map<Integer, String> timeMap = new HashMap<>();
    public Map<Integer, String> shapeMap = new HashMap<>();
    public Map<Integer, String> phyElectricMap = new HashMap<>();
    public Map<Integer, String> phySoundMap = new HashMap<>();
    public Map<Integer, String> phyNuclearMap = new HashMap<>();
    public Map<Integer, String> phyRadarMap = new HashMap<>();
    public Map<Integer, String> materialMap = new HashMap<>();
    public Map<Integer, String> passAbiMap = new HashMap<>();
    public Map<Integer, String> motionFeaMap = new HashMap<>();

    //初始化受控映射表
    public void init(){
        spaceLocMap.put(1,"BDS");
        spaceLocMap.put(2,"GPS");

        timeMap.put(1,"BDS");
        timeMap.put(2,"GPS");

        //几何轮廓受控映射表
        shapeMap.put(0,"圆形");
        shapeMap.put(1,"长方形");
        shapeMap.put(2,"正方形");
        shapeMap.put(3,"菱形");
        shapeMap.put(4,"不规则图形");
        shapeMap.put(5,"球");
        shapeMap.put(6,"长方体");
        shapeMap.put(7,"正方体");
        shapeMap.put(8,"圆柱体");

        //电磁特性受控映射表
        phyElectricMap.put(0,"反射电磁波");
        phyElectricMap.put(1,"不反射电磁波");

        //声波特性受控映射表
        phySoundMap.put(0,"反射声波");
        phySoundMap.put(1,"不反射声波");

        //核生化特性受控映射表
        phyNuclearMap.put(0,"产生核辐射");
        phyNuclearMap.put(1,"不产生核辐射");

        //雷达特性受控映射表
        phyRadarMap.put(0,"可被雷达检测");
        phyRadarMap.put(1,"不可被雷达检测");

        //材质受控映射表
        materialMap.put(0,"金属材质");
        materialMap.put(1,"塑料材质");
        materialMap.put(2,"木材质");
        materialMap.put(3,"水泥材质");
        materialMap.put(4,"石材质");
        materialMap.put(5,"无机非金属材质");
        materialMap.put(6,"有机高分子材质");
        materialMap.put(7,"合成橡胶材质");
        materialMap.put(8,"合成纤维");

        //通过程度受控映射表
        passAbiMap.put(0,"不可通过");
        passAbiMap.put(1,"可通过");

        motionFeaMap.put(1,"速度单位为厘米每秒（cm/s），加速度单位为厘米每秒平方（cm/s2）");
        motionFeaMap.put(2,"速度单位为米每秒（m/s），加速度单位为米每秒平方（m/s2）");
        motionFeaMap.put(3,"速度单位为千米每小时（km/h），加速度单位为厘米每秒平方（km/h2）");
        motionFeaMap.put(4,"速度单位为英里每小时（mile/h），加速度单位为英里每小时平方（mile/h2）");
    }

    public void setPropertyMap(Map<Integer, String> propertySetCategoryMap, Map<Integer, Map<Integer, String>> propertyCategoryMap){

        motionFeaMap.put(2,"速度单位为英里每小时（mile/h），加速度单位为英里每小时平方（mile/h2）");

        Map<Integer, String> zcps = new HashMap<Integer, String>();
        materialMap.put(1,"目标对象名称");
        materialMap.put(2,"空间位置信息");
        materialMap.put(3,"时间信息");
        materialMap.put(4,"目标外形");
        materialMap.put(5,"物理特性");
        materialMap.put(6,"目标材质");
        materialMap.put(7,"通过程度");
        materialMap.put(8,"空间敌我态势");
        materialMap.put(11,"绝对运动特性");
        materialMap.put(12,"相对运动特性");
        materialMap.put(13,"运行轨迹");

        propertyCategoryMap.put(2,zcps);
    }

    public String getMapValue(Map<Integer, String> m, int key){
        if (!m.containsKey(key)) {
            return "无该映射！！！";
        }
        return m.get(key);
    }

    public void showProperty(UCLPropertyBase property){
        switch (property.getCategory())
        {
            case 1:
                showName(property);
                break;
            case 2:
                showSpaceLoc(property);
                break;
            case 3:
                showTime(property);
                break;
            case 4:
                showShape(property);
                break;
            case 5:
                showPhysical(property);
                break;
            case 6:
                showMaterial(property);
                break;
            case 7:
                showPassingAbility(property);
                break;
            case 8:
                showSpaceEnemyS(property);
                break;
            case 11:
            case 12:
                showMotionFea(property);
                break;
            case 13:
                showTravellingPath(property);
        }
    }

    public void showName(UCLPropertyBase propertyBase){
        System.out.println("名称:"+propertyBase.getVPart());
    }

    public void showSpaceLoc(UCLPropertyBase propertyBase){
        int parse = propertyBase.getLPartHead(2, 5);
        System.out.println("坐标体系描述位置标准:"+getMapValue(spaceLocMap, parse));
        System.out.println("位置坐标:"+propertyBase.getVPart());
    }

    public void showTime(UCLPropertyBase propertyBase){
        int parse = propertyBase.getLPartHead(2, 5);
        System.out.println("授时信息规则:"+getMapValue(timeMap, parse));
        System.out.println("时间:"+propertyBase.getVPart());
    }

    public String[] split(String str, String pattern) {
        String[] res = str.split(pattern);
        return res;
    }

    public String[] getShape(String vPart){
        return split(vPart, DEFAULT_PATTERN);
    }

    public void showShape(UCLPropertyBase propertyBase){
        String[] shapes = getShape(propertyBase.getVPart());
        String shape = shapes[0];
        String loc = shapes[1];

        System.out.println("几何轮廓:"+shape+", " +getMapValue(shapeMap, Integer.parseInt(shape)));
        System.out.println("投影边界:"+loc);
    }

    public String[] getPhysical(String vPart){
        return split(vPart, DEFAULT_PATTERN);
    }
    public void showPhysical(UCLPropertyBase propertyBase){
        String[] physicals = getPhysical(propertyBase.getVPart());
        String electric = physicals[0];
        String sound = physicals[1];
        String nuclear = physicals[2];
        String radar = physicals[3];

        System.out.println("电磁特性:"+electric+", " +getMapValue(phyElectricMap, Integer.parseInt(electric)));
        System.out.println("声波特性:"+sound+", " +getMapValue(phySoundMap, Integer.parseInt(sound)));
        System.out.println("核生化特性:"+nuclear+", " +getMapValue(phyNuclearMap, Integer.parseInt(nuclear)));
        System.out.println("雷达特性:"+radar+", " +getMapValue(phyRadarMap, Integer.parseInt(radar)));
    }

    public void showMaterial(UCLPropertyBase propertyBase){
        String value = propertyBase.getVPart();
        System.out.println("材质:"+value+", " +getMapValue(materialMap, Integer.parseInt(value)));
    }
    public void showPassingAbility(UCLPropertyBase propertyBase){
        String value = propertyBase.getVPart();
        System.out.println("通过程度:"+value+", " +getMapValue(passAbiMap, Integer.parseInt(value)));
    }
    public void showSpaceEnemyS(UCLPropertyBase propertyBase){
        String[] spaceStatus = getPhysical(propertyBase.getVPart());
        String group = spaceStatus[0];
        String sky = spaceStatus[1];
        String water = spaceStatus[2];

        System.out.println("地面敌我状态信息:"+group);
        System.out.println("空中敌我环境信息:"+sky);
        System.out.println("水下敌我状况信息:"+water);
    }
    public void showMotionFea(UCLPropertyBase propertyBase){
        int parse = propertyBase.getLPartHead(2, 5);
        String[] motions = getPhysical(propertyBase.getVPart());

        System.out.println("运动特性解析信息:"+getMapValue(motionFeaMap, parse));
        System.out.println("速度:"+motions[0]);
        System.out.println("加速度:"+motions[1]);
        System.out.println("运动轨迹:"+motions[2]);
    }
    public void showTravellingPath(UCLPropertyBase propertyBase){
        int parse = propertyBase.getLPartHead(2, 5);

        System.out.println("运动特性解析信息:"+getMapValue(spaceLocMap, parse));
        System.out.println("运动轨迹:"+propertyBase.getVPart());
    }
}
