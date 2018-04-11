package com.book.core.serializable;

import java.io.Serializable;

public class SerializationUtil {
	
    public static ProtostuffSerializer protostuffSerializer;

	static {
		protostuffSerializer = new ProtostuffSerializer();
	}


	public static byte[] object2Bytes(Serializable obj) throws Exception {
		if (obj == null) {
			return null;
		}

		return protostuffSerializer.serialize(obj);

/*		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(obj);
		bo.close();
		oo.close();
		return bo.toByteArray();*/
	}
	
	/**
	 * 序列化【序列化对象不需要实现Serializable】
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static byte[] object2Bytes_obj(Object obj) throws Exception {
		if (obj == null) {
			return null;
		}
		
		return protostuffSerializer.serialize(obj);
	}

	public static byte[][] objects2Bytes(Serializable[] obj) throws Exception {
		if (obj == null) {
			return null;
		}
		byte[][] many = new byte[obj.length][];
		for(int i=0;i<obj.length;i++){
			many[i] = object2Bytes(obj[i]);
		}
		return many;
	}


	public static Object bytes2Object(byte[] objBytes) throws Exception {
		if (objBytes == null || objBytes.length == 0) {
			return null;
		}
		Object obj = protostuffSerializer.deserialize(objBytes);
		return obj;

		/*ByteArrayInputStream bi = new ByteArrayInputStream(objBytes);
		ObjectInputStream oi = new ObjectInputStream(bi);
		obj = oi.readObject();
		bi.close();
		oi.close();
		return obj;*/
	}

}
