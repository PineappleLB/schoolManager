package club.pinea.school.config;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;  

public class IpConvert extends ClassicConverter {  
  
	private static final Logger logger = LoggerFactory.getLogger(IpConvert.class);
    @Override  
    public String convert(ILoggingEvent event) {
    	String ip = "";
        InetAddress ia=null;
        try {
        	if(ia == null){
        		ia = InetAddress.getLocalHost();
        	}
            String localname=ia.getHostName();
            String localip=ia.getHostAddress();
            ip+="["+localname+"|"+localip;
        } catch (Exception e) {
            logger.error("{}",e);
        }  
        return ip;
    }  
}  
