package com.example.Kyrcach_java_Yaroslavtser;

import com.example.Kyrcach_java_Yaroslavtser.model.DogovorStatus;
import com.example.Kyrcach_java_Yaroslavtser.model.OrderStatus;
import com.example.Kyrcach_java_Yaroslavtser.model.YchetStatus;
import com.example.Kyrcach_java_Yaroslavtser.model.YchetStatus_admin;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.management.relation.Role;

@SpringBootTest
class KyrcachJavaYaroslavtserApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testEnum_Dogovor() {
		String a = String.valueOf(DogovorStatus.Заключен);
		Assert.isTrue(String.valueOf(DogovorStatus.Заключен).equals(a));
	}
	@Test
	void testEnum_Order() {
	String b = String.valueOf(OrderStatus.Расходы);
	Assert.isTrue(String.valueOf(OrderStatus.Расходы).equals(b));
	}
	@Test
	void testEnum_Ychet() {
		String c = String.valueOf(YchetStatus.Бухгалтерский);
		Assert.isTrue(String.valueOf(YchetStatus.Бухгалтерский).equals(c));
	}
	@Test
	void testEnum_YchetStatus() {
		String d = String.valueOf(YchetStatus_admin.Оформлен);
		Assert.isTrue(String.valueOf(YchetStatus_admin.Оформлен).equals(d));

	}
}
