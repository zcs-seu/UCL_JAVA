package ucl.property.generate;

import ucl.property.base.UCLPropertyBase;

/**
 * Created by seu on 2018/3/29.
 */
public class GenZCPSProperty {

    //目标对象名称,此处c++版本中helper参数采用默认参数值0的形式实现，Java不支持，特此说明
    public static UCLPropertyBase genName(String vPart,int helper){
        UCLPropertyBase name = new UCLPropertyBase();
        name.setProperty(0x01, helper, vPart);

        return name;
    }

    //空间位置信息, 默认解析规则为GPS
    /**
     *
     * @param vPart
     * @param parse 坐标体系描述位置信息规则
     * 0001 表示采用中国北斗卫星导航系统（BDS）坐标体系描述位置信息；
     * 0010 表示采用全球定位系统（GPS）坐标体系描述位置信息；
     * c++版本中采用默认参数值2的形式实现，Java不支持，特此说明
     * @param helper
     * c++版本中采用默认参数值0的形式实现，Java不支持，特此说明
     * @return
     */
    public static UCLPropertyBase genSpaceLoc(String vPart, int parse, int helper){

        UCLPropertyBase spaceLoc = new UCLPropertyBase();
        spaceLoc.setLPartHead(2, 5, parse);
        spaceLoc.setProperty(0x2, helper, vPart);

        return spaceLoc;
    }


    //时间信息, 默认为GPS授时时间
    /**
     *
     * @param vPart
     * @param parse 授时信息规则
     * 0001表示采用中国北斗卫星导航系统（BDS）授时信息；
     * 0010表示POSIX时间标准时间；
     * c++版本中采用默认参数值2的形式实现，Java不支持，特此说明
     * @param helper
     * c++版本中采用默认参数值0的形式实现，Java不支持，特此说明
     * @return
     */
    public static UCLPropertyBase genTime(String vPart, int parse, int helper){
        UCLPropertyBase time = new UCLPropertyBase();
        time.setLPartHead(2, 5, parse);
        time.setProperty(0x3, helper, vPart);

        return time;
    }


    /**
    *目标外形包括几何轮廓，投影边界两部分，以西文分隔符“;”分割，必须有一个";",若某部分为空该部分可省略，但逗号必须：
    *第一部分几何轮廓，根据受控映射表进行取值；
    *第二部分投影边界，空间位置坐标集合。
    */
    static UCLPropertyBase genShape(String vPart, int helper){
        UCLPropertyBase shape = new UCLPropertyBase();
        shape.setProperty(0x4, helper, vPart);

        return shape;
    }


    /**
    *物理特性包括了电磁特性，声波特性和核生化特性三部分，以西文分隔符“;”分割，，必须有三个";",若某部分为空该部分可省略，但逗号必须：
    *第一部分电磁特性，根据电磁特性受控映射表进行取值；
    *第二部分声波特性，根据声波特性受控映射表进行取值；
    *第三部分核生化特性，根据核生化特性受控映射表进行取值。
    *第四部分雷达特性，根据雷达特性受控映射表进行取值。
    */
    public static UCLPropertyBase genPhysical(String vPart, int helper){
        UCLPropertyBase physical = new UCLPropertyBase();
        physical.setProperty(0x5, helper, vPart);

        return physical;
    }

    //目标材质
    public static UCLPropertyBase genMaterial(String vPart, int helper){
        UCLPropertyBase material = new UCLPropertyBase();
        material.setProperty(0x6, helper, vPart);

        return material;
    }

    //通过程度
    public static UCLPropertyBase genPassingAbility(String vPart, int helper){
        UCLPropertyBase pass = new UCLPropertyBase();
        pass.setProperty(0x7, helper, vPart);

        return pass;
    }

    //空间敌我态势(Space Enemy Situation)
    /**
    *空间状况信息地面敌我状况信息，空中敌我状况信息和水下敌我状况信息三个部分，以西文分隔符“;”分割，必须有两个";",若某部分为空该部分可省略，但逗号必须：
    *第一部分地面敌我状况信息；
    *第二部分空中敌我状况信息；
    *第三部分水下敌我状况信息。
    */
    public static UCLPropertyBase genSpaceEnemyS(String vPart, int helper){
        UCLPropertyBase spaceStatus = new UCLPropertyBase();
        spaceStatus.setProperty(0x8, helper, vPart);

        return spaceStatus;
    }

    /**
     * 绝对运动特性
     * 默认规则为：m/s, m/s*s
     * 绝对运动特性包括绝对速度，绝对加速度，运行轨迹三部分，以西文分隔符“;”分割，必须有两个";",若某部分为空该部分可省略，但逗号必须：
     * 第一部分绝对速度为数值；
     * 第二部分绝对加速度为数值；
     * 第三部分运行轨迹为坐标集合。
     */
    public static UCLPropertyBase genAbsMotionFea(String vPart, int parse, int helper){
        UCLPropertyBase absMontionFea = new UCLPropertyBase();
        absMontionFea.setLPartHead(2, 5, parse);
        absMontionFea.setProperty(11, helper, vPart);

        return absMontionFea;
    }

    /**
     * 相对运动特性
     * 默认规则为：m/s, m/s*s
     * 相对运动特性包括绝对速度，绝对加速度，运行轨迹三部分，以西文分隔符“;”分割，必须有两个";",若某部分为空该部分可省略，但逗号必须：
     * 第一部分绝对速度为数值；
     * 第二部分绝对加速度为数值；
     * 第三部分运行轨迹为坐标集合。
    */
    public static UCLPropertyBase genRelMotionFea(String vPart, int parse, int helper){
        UCLPropertyBase relMontionFea = new UCLPropertyBase();
        relMontionFea.setLPartHead(2, 5, parse);
        relMontionFea.setProperty(12, helper, vPart);

        return relMontionFea;
    }

    //运行轨迹, 默认解析规则为GPS
    /**
     *
     * @param vPart
     * @param parse
     * 0001表示采用中国北斗卫星导航系统（BDS）坐标体系描述位置信息；
     * 0010表示采用全球定位系统（GPS）坐标体系描述位置信息；
     * 默认规则为GPS
     * @param helper
     * @return
     */
    public static UCLPropertyBase genTravellingPath(String vPart, int parse, int helper){
        UCLPropertyBase travellingPath = new UCLPropertyBase();
        travellingPath.setLPartHead(2, 5, parse);
        travellingPath.setProperty(13, helper, vPart);

        return travellingPath;
    }
}
