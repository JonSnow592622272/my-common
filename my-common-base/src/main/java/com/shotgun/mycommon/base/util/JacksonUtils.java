package com.shotgun.mycommon.base.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class JacksonUtils {

    /**
     * 统一默认时间格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * final ObjectMapper mapper = new ObjectMapper(); // can use static singleton,
     * inject: just make sure to reuse!
     */
    public static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper().setDateFormat(new SimpleDateFormat(DEFAULT_DATE_FORMAT));

        // jackson配置*****************************************
        // jackson忽略不存在的属性
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        SimpleModule simpleModule = new SimpleModule();

        simpleModule.addDeserializer(IPage.class, new JsonDeserializer<IPage>() {
            @Override
            public IPage deserialize(JsonParser p,
                    DeserializationContext ctxt) throws IOException, JsonProcessingException {
                return p.readValueAs(Page.class);
            }
        });

        OBJECT_MAPPER.registerModule(simpleModule);

    }

    /**
     * 对象转json
     */
    public static String writeValueAsString(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * json转对象（单个）
     */
    public static <T> T readValue(String json, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(json, valueType);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }


    /**
     * @author wulm
     * @desc json转对象（支持集合等所有类型）
     * 比如：List<Aaa> o = OBJECT_MAPPER.readValue(json, new TypeReference<List<Aaa>>() {});
     **/
    public static <T> T readValue(String json, TypeReference<T> valueTypeRef) {

        try {
            return OBJECT_MAPPER.readValue(json, valueTypeRef);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }


//	/**
//	 *
//	 * @desc 测试
//	 * @auth wulm
//	 * @date 2018年6月20日 上午11:12:14
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		/* "hello", 666, new Date() */
//		Aaa a = new Aaa();
//		a.setA_id("aaa");
//
//		System.out.println(JacksonUtils.writeValueAsString(a));
//
//		Aaa b = JacksonUtils.readValue("{\"a_id\":\"aaa\",\"b\":\"bbb\",\"c\":\"ccc\"}", Aaa.class);
//		System.out.println(JacksonUtils.writeValueAsString(b));
//
//
//	}
//
//	public static class Aaa {
//		private String a_id;
//
//		public String getA_id() {
//			System.out.println("hahahhahhhhhhhhhhhhhhhhhh");
//			return a_id;
//		}
//
//		public void setA_id(String a_id) {
//			this.a_id = a_id;
//		}
//	}

}
