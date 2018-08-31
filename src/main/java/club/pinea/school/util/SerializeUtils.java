package club.pinea.school.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.shiro.session.Session;

public class SerializeUtils {

	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
	    ByteArrayOutputStream bos = null;
	    try {
	        bos = new ByteArrayOutputStream();
	        oos = new ObjectOutputStream(bos);
	        oos.writeObject(object);
	        byte[] b = bos.toByteArray();
	        return b;
	    } catch (IOException e) {
	        System.out.println("序列化失败 Exception:" + e.toString());
	        return null;
	    } finally {
	        try {
	            if (oos != null) {
	                oos.close();
	            }
	            if (bos != null) {
	                bos.close();
	            }
	        } catch (IOException ex) {
	            System.out.println("io could not close:" + ex.toString());
	        }
	    }
	}

	public static Session deserialize(byte[] value) {
		ByteArrayInputStream bais = null;
	    try {
	        // 反序列化
	        bais = new ByteArrayInputStream(value);
	        ObjectInputStream ois = new ObjectInputStream(bais);
	        return (Session) ois.readObject();
	    } catch (IOException | ClassNotFoundException e) {
	        System.out.println("bytes Could not deserialize:" + e.toString());
	        return null;
	    } finally {
	        try {
	            if (bais != null) {
	                bais.close();
	            }
	        } catch (IOException ex) {
	            System.out.println("LogManage Could not serialize:" + ex.toString());
	        }
	    }
	}

}
