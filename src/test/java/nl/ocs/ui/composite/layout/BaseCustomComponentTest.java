package nl.ocs.ui.composite.layout;

import java.math.BigDecimal;
import java.util.Calendar;

import junitx.util.PrivateAccessor;
import nl.ocs.domain.TestEntity;
import nl.ocs.domain.TestEntity.TestEnum;
import nl.ocs.domain.TestEntity2;
import nl.ocs.domain.model.EntityModel;
import nl.ocs.domain.model.EntityModelFactory;
import nl.ocs.domain.model.impl.EntityModelFactoryImpl;
import nl.ocs.service.MessageService;
import nl.ocs.test.BaseMockitoTest;
import nl.ocs.test.MockitoSpringUtil;
import nl.ocs.utils.DateUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.vaadin.ui.Label;

public class BaseCustomComponentTest extends BaseMockitoTest {

	private EntityModelFactory factory = new EntityModelFactoryImpl();

	private BaseCustomComponent component = new BaseCustomComponent() {

		private static final long serialVersionUID = -714656253533978108L;

		@Override
		public void build() {

		}
	};

	@Mock
	private MessageService messageService;

	@Before
	public void setup() throws NoSuchFieldException {
		MockitoSpringUtil.mockMessageService(messageService);
		PrivateAccessor.setField(component, "messageService", messageService);
		PrivateAccessor.setField(factory, "defaultPrecision", 2);
	}

	@Test
	public void test() {
		EntityModel<TestEntity> model = factory.getModel(TestEntity.class);

		TestEntity e = new TestEntity("Kevin", 12L);
		e.setDiscount(BigDecimal.valueOf(12.34));
		e.setBirthDate(DateUtils.createDate("04052016"));
		e.setBirthWeek(DateUtils.createDate("04052016"));
		e.setSomeInt(1234);

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 14);
		cal.set(Calendar.MINUTE, 25);
		cal.set(Calendar.SECOND, 37);
		e.setSomeTime(cal.getTime());

		e.setSomeEnum(TestEnum.A);
		e.setSomeBoolean(Boolean.TRUE);
		e.setSomeBoolean2(Boolean.TRUE);

		TestEntity2 te2 = new TestEntity2();
		te2.setName("Bob");
		te2.setId(2);
		e.addTestEntity2(te2);

		TestEntity2 te3 = new TestEntity2();
		te3.setName("Stuart");
		te3.setId(3);
		e.addTestEntity2(te3);

		Label label = (Label) component.constructLabel(e, model.getAttributeModel("name"));
		Assert.assertEquals("Kevin", label.getValue());

		// integer
		label = (Label) component.constructLabel(e, model.getAttributeModel("someInt"));
		Assert.assertEquals("1.234", label.getValue());

		// long
		label = (Label) component.constructLabel(e, model.getAttributeModel("age"));
		Assert.assertEquals("12", label.getValue());

		// BigDecimal
		label = (Label) component.constructLabel(e, model.getAttributeModel("discount"));
		Assert.assertEquals("12,34", label.getValue());

		// date
		label = (Label) component.constructLabel(e, model.getAttributeModel("birthDate"));
		Assert.assertEquals("04/05/2016", label.getValue());

		// week
		label = (Label) component.constructLabel(e, model.getAttributeModel("birthWeek"));
		Assert.assertEquals("2016-18", label.getValue());

		// time
		label = (Label) component.constructLabel(e, model.getAttributeModel("someTime"));
		Assert.assertEquals("14:25:37", label.getValue());

		// enum
		label = (Label) component.constructLabel(e, model.getAttributeModel("someEnum"));
		Assert.assertEquals("A", label.getValue());

		// entity collection
		label = (Label) component.constructLabel(e, model.getAttributeModel("testEntities"));
		Assert.assertEquals("Bob, Stuart", label.getValue());

		// boolean
		label = (Label) component.constructLabel(e, model.getAttributeModel("someBoolean"));
		Assert.assertEquals("true", label.getValue());

		// boolean with overwritten value
		label = (Label) component.constructLabel(e, model.getAttributeModel("someBoolean2"));
		Assert.assertEquals("On", label.getValue());
	}

}
