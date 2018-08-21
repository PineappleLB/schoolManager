package club.pinea.school.config;
import ch.qos.logback.classic.PatternLayout;  

public class MyPatternLayout extends PatternLayout {  
    static {  
        defaultConverterMap.put("ip",IpConvert.class.getName());  
    }  
}  