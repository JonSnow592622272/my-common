package com.shotgun.mycommon.base.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.shotgun.mycommon.base.base.api.ViewEnum;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class JacksonUtilsSpringmvc {

	/**
	 * final ObjectMapper mapper = new ObjectMapper(); // can use static singleton,
	 * inject: just make sure to reuse!
	 */
	public static final ObjectMapper OBJECT_MAPPER;
	static {
		OBJECT_MAPPER = new ObjectMapper().setDateFormat(new SimpleDateFormat(JacksonUtils.DEFAULT_DATE_FORMAT));

		// jackson配置*****************************************
		// jackson忽略不存在的属性
		OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// 防止js中Long类型丢失精度，增加枚举映射
		SimpleModule simpleModule = new SimpleModule();

		simpleModule.addSerializer(Long.class, ToStringSerializer.instance)
				.addSerializer(Long.TYPE, ToStringSerializer.instance)
				.addSerializer(ViewEnum.class, new JsonSerializer<ViewEnum>() {
					// 返回给前端的枚举进行处理
					@Override
					public void serialize(ViewEnum value, JsonGenerator gen, SerializerProvider serializers)
							throws IOException {
						gen.writeStartObject();
						gen.writeFieldName("text");
						gen.writeString(value.text());
						gen.writeFieldName("value");
						gen.writeString(value.value());
						gen.writeEndObject();
					}
				}).addDeserializer(IPage.class, new JsonDeserializer<IPage>() {
			@Override
			public IPage deserialize(JsonParser p,
					DeserializationContext ctxt) throws IOException, JsonProcessingException {
				return p.readValueAs(Page.class);
			}
		});
		OBJECT_MAPPER.registerModule(simpleModule);
		// jackson序列化处理
		OBJECT_MAPPER.setSerializerFactory(
				OBJECT_MAPPER.getSerializerFactory().withSerializerModifier(new MyBeanSerializerModifier()));

	}

	/** 对象转json */
	public static String writeValueAsString(Object obj) {
		try {
			return OBJECT_MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/** json转对象（单个） */
	public static <T> T readValue(String json, Class<T> valueType) {
		try {
			return OBJECT_MAPPER.readValue(json, valueType);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/** json转对象（支持集合等所有类型） */
	public static <T> T readValue(String json, TypeReference<T> valueTypeRef) {
		try {
			return OBJECT_MAPPER.readValue(json, valueTypeRef);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 *
	 * @desc jackson序列化处理
	 * @author wulm
	 * @date 2018年6月20日 上午11:08:08
	 * @version 1.0.0
	 */
	public static class MyBeanSerializerModifier extends BeanSerializerModifier {

		@Override
		public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
				List<BeanPropertyWriter> beanProperties) {
			// 循环所有的beanPropertyWriter
			for (BeanPropertyWriter writer : beanProperties) {

				// 判断字段的类型，如果是array，list，set则注册nullSerializer
				Class<?> rawClass = writer.getType().getRawClass();

				if (Integer.class.isAssignableFrom(rawClass) || Byte.class.isAssignableFrom(rawClass)
						|| Short.class.isAssignableFrom(rawClass) || int.class.isAssignableFrom(rawClass)
						|| byte.class.isAssignableFrom(rawClass) || short.class.isAssignableFrom(rawClass)) {

					writer.assignNullSerializer(MyBeanSerializerModifier.myNullIntegerJsonSerializer);
				} else if (Long.class.isAssignableFrom(rawClass) || long.class.isAssignableFrom(rawClass)) {

					writer.assignNullSerializer(MyBeanSerializerModifier.myNullLongJsonSerializer);
				} else if (Double.class.isAssignableFrom(rawClass) || Float.class.isAssignableFrom(rawClass)
						|| double.class.isAssignableFrom(rawClass) || float.class.isAssignableFrom(rawClass)) {

					writer.assignNullSerializer(MyBeanSerializerModifier.myNullDoubleJsonSerializer);
				} else if (CharSequence.class.isAssignableFrom(rawClass)) {

					writer.assignNullSerializer(MyBeanSerializerModifier.myNullStringJsonSerializer);
				} else if (Boolean.class.isAssignableFrom(rawClass) || boolean.class.isAssignableFrom(rawClass)) {

					writer.assignNullSerializer(MyBeanSerializerModifier.myNullStringJsonSerializer);
				} else if (rawClass.isArray() || List.class.isAssignableFrom(rawClass)
						|| Set.class.isAssignableFrom(rawClass)) {

					// 给writer注册一个自己的nullSerializer，集合
					writer.assignNullSerializer(MyBeanSerializerModifier.myNullArrayJsonSerializer);
				} else if (Date.class.isAssignableFrom(rawClass)) {

					// 给writer注册一个自己的nullSerializer，时间
					writer.assignNullSerializer(MyBeanSerializerModifier.myNullDateJsonSerializer);
				} else {
					writer.assignNullSerializer(MyBeanSerializerModifier.myNullObjectJsonSerializer);
				}

			}

			return beanProperties;
		}

		public static final JsonSerializer<Object> myNullIntegerJsonSerializer = new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
				gen.writeNumber(0);
			}
		};
		public static final JsonSerializer<Object> myNullLongJsonSerializer = new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
				// Long类型使用字符串，避免js丢失精度
				gen.writeString("0");
			}
		};
		public static final JsonSerializer<Object> myNullDoubleJsonSerializer = new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
				gen.writeNumber(0.0);
			}
		};
		public static final JsonSerializer<Object> myNullStringJsonSerializer = new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
				gen.writeString("");
			}
		};

		public static final JsonSerializer<Object> myNullDateJsonSerializer = new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
				gen.writeString("");
			}
		};

		public static final JsonSerializer<Object> myNullArrayJsonSerializer = new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
				gen.writeStartArray();
				gen.writeEndArray();
			}
		};

		public static final JsonSerializer<Object> myNullObjectJsonSerializer = new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
				gen.writeStartObject();
				gen.writeEndObject();
			}
		};
	}

//	/**
//	 * 
//	 * @desc 测试
//	 * @auth wulm
//	 * @date 2018年6月20日 上午11:12:14
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		Aaa aaa = new Aaa(/* "hello", 666, new Date() */);
//
//		// 对象转json
//		String json = writeValueAsString(aaa);
//		System.out.println("json::::" + json);
//
//		System.out.println(writeValueAsString(new ResultInfo<>(SystemCodeEnum.OPERATION, CodeEnum.ERROR)));
//
//		// json转对象
//		Aaa he = readValue(json, Aaa.class);
//		System.out.println(he + ";;;;;;;;;");
//
//		List<Aaa> list = new ArrayList<>();
//		list.add(aaa);
//		list.add(aaa);
//		String j = writeValueAsString(list);
//
//		// json转集合
//		List<Aaa> aaaList = readValue(j, new TypeReference<List<Aaa>>() {
//		});
//
//		System.out.println(aaaList);
//		for (Aaa aaa2 : aaaList) {
//			System.out.println("json转对象值!!!!!!!!");
//			System.out.println(aaa2.getF1());
//			System.out.println(aaa2.getF2());
//			System.out.println(aaa2.getF3());
//		}
//
//	}
//
//	/**
//	 * 
//	 * @desc 测试类
//	 * @author wulm
//	 * @date 2018年6月20日 上午11:12:05
//	 * @version 1.0.0
//	 */
//	@JacksonXmlRootElement(namespace = "ssss", localName = "XML")
//	public static class Aaa {
//
//		private Date f1;
//		private List<String> f2;
//		private String[] f3;
//		@JacksonXmlCData
//		private String f4;
//		private StringBuilder f5;
//
//		private Byte f14;
//		private Short f15;
//		private Integer f16;
//		private Long f17;
//		private Float f18;
//		private Double f19;
//		private Boolean f20;
//
//		public Aaa() {
//			super();
//		}
//
//		public Date getF1() {
//			return f1;
//		}
//
//		public void setF1(Date f1) {
//			this.f1 = f1;
//		}
//
//		public List<String> getF2() {
//			return f2;
//		}
//
//		public void setF2(List<String> f2) {
//			this.f2 = f2;
//		}
//
//		public String[] getF3() {
//			return f3;
//		}
//
//		public void setF3(String[] f3) {
//			this.f3 = f3;
//		}
//
//		public String getF4() {
//			return f4;
//		}
//
//		public void setF4(String f4) {
//			this.f4 = f4;
//		}
//
//		public StringBuilder getF5() {
//			return f5;
//		}
//
//		public void setF5(StringBuilder f5) {
//			this.f5 = f5;
//		}
//
//		public Byte getF14() {
//			return f14;
//		}
//
//		public void setF14(Byte f14) {
//			this.f14 = f14;
//		}
//
//		public Short getF15() {
//			return f15;
//		}
//
//		public void setF15(Short f15) {
//			this.f15 = f15;
//		}
//
//		public Integer getF16() {
//			return f16;
//		}
//
//		public void setF16(Integer f16) {
//			this.f16 = f16;
//		}
//
//		public Long getF17() {
//			return f17;
//		}
//
//		public void setF17(Long f17) {
//			this.f17 = f17;
//		}
//
//		public Float getF18() {
//			return f18;
//		}
//
//		public void setF18(Float f18) {
//			this.f18 = f18;
//		}
//
//		public Double getF19() {
//			return f19;
//		}
//
//		public void setF19(Double f19) {
//			this.f19 = f19;
//		}
//
//		public Boolean getF20() {
//			return f20;
//		}
//
//		public void setF20(Boolean f20) {
//			this.f20 = f20;
//		}
//
//	}
}
