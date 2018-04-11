package com.book.core.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.book.core.model.Type;
import com.book.core.serializable.SerializationUtil;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

/**
 * ProtoStuff测试
 * @author liweihan
 *
 */
public class TestProtoStuff {
	public static void main(String[] args) throws Exception {
		
		/**
		 * *********** 测试1 : 原始的序列化对象 ************
		 */
		//序列化
		System.out.println(" ========= 序列化开始:" );
		Schema<Type> schema = RuntimeSchema.getSchema(Type.class);
		Type type = new Type();
		type.setCityId(1);
		type.setPrice(new BigDecimal(100));
		type.setTypeName("韩超");
		
		LinkedBuffer buffer = LinkedBuffer.allocate(1024);
		byte[] data = ProtobufIOUtil.toByteArray(type, schema, buffer);
		System.out.println("序列化后的大小:" + data.length + " 字节 !");
		
		//反序列化
		System.out.println(" ========= 反序列化开始:" );
		Type type2 = new Type();
		ProtobufIOUtil.mergeFrom(data, type2, schema);
		System.out.println(" ====== 反序列化后的结果为：cityId:" + type2.getCityId() 
				+ " ,typeName:" + type2.getTypeName() 
				+ " , price:" + type2.getPrice());
		
		
		/**
		 * ************ 测试2 ：单独序列化集合 **************
		 */
		Type t1 = new Type();
		t1.setId(1);
		t1.setCityId(1);
		t1.setPrice(new BigDecimal(1));
		t1.setTypeName("TestHan");
		
		List<Type> list1 = new ArrayList<Type>();
		list1.add(t1);
		list1.add(type);
		
		System.out.println(" *********** 序列化开始： ");
		List<byte[]> result = serializeProtoStuffTypeList(list1);
		System.out.println("序列化后集合的大小:" + result.size());
		
		System.out.println(" *********** 反序列化开始： ");
		List<Type> l = deserializeProtoStuffToTypeList(result);
		System.out.println(" 反序列化后的集合大小为:" + l.size() + " , name1:" + l.get(0).getTypeName());
		
		/*********** 测试 3 *****************/
		Type type1 = new Type();
		type1.setCityId(2);
		type1.setPrice(new BigDecimal(100));
		type1.setTypeName("太");
		
		System.out.println(" ------ 序列化开始：");
		byte[] type1Ser = SerializationUtil.object2Bytes_obj(type1);
		System.out.println(" ------- 序列化后的大小：" + type1Ser.length);
		
		System.out.println(" ------ 反序列化开始：");
		Type type1Result = (Type)SerializationUtil.bytes2Object(type1Ser);
		System.out.println(" ====== 反序列化后的结果为：cityId:" + type1Result.getCityId() 
				+ " ,typeName:" + type1Result.getTypeName() 
				+ " , price:" + type1Result.getPrice());
		
	
		/******************** 测试4 :序列化集合 **********************/
		Type t2 = new Type();
		t2.setId(2);
		t2.setCityId(2);
		t2.setPrice(new BigDecimal(23));
		t2.setTypeName("ZHANG");
		
		ArrayList<Type> list2 = new ArrayList<Type>();
		list2.add(t2);
		list2.add(t1);
		
		System.out.println(" ++++++++++++++   序列化开始： ");
		byte[] result2 =  SerializationUtil.object2Bytes(list2);
		System.out.println(" 序列化的大小： " + result2.length);
		
		System.out.println(" ++++++++++++++   序列化结束： ");
		List<Type> listResult = (List<Type>)SerializationUtil.bytes2Object(result2);
		for (Type t: listResult) {
			System.out.println(t.getTypeName());
		}
	}
	
	/**
	 * 序列化Type的List集合
	 * @param tList
	 * @return
	 */
	public static List<byte[]> serializeProtoStuffTypeList(List<Type> tList) {
		if (tList == null || tList.size() <= 0) {
			return null;
		}
		
		List<byte[]> bytes = new ArrayList<byte[]>();
		Schema<Type> schema = RuntimeSchema.getSchema(Type.class);
		LinkedBuffer buffer = LinkedBuffer.allocate(1024);
		byte[] protostuff = null;
		for(Type t: tList) {
			try {
				protostuff = ProtostuffIOUtil.toByteArray(t, schema, buffer);
				bytes.add(protostuff);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				buffer.clear();
			}
		}
		
		return bytes;
	}
	
	/**
	 * 反序列化Type的List集合
	 * @param bytesList
	 * @return
	 */
	public static List<Type> deserializeProtoStuffToTypeList(List<byte[]> bytesList) {
		if (bytesList == null || bytesList.size() <= 0) {
			return null;
		}
		
		Schema<Type> schema = RuntimeSchema.getSchema(Type.class);
		List<Type> list = new ArrayList<Type>();
		for (byte[] bs : bytesList) {
			Type type = new Type();
			ProtostuffIOUtil.mergeFrom(bs, type, schema);
			list.add(type);
		}
		return list;
	}
}


