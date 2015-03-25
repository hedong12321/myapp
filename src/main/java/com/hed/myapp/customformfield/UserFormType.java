package com.hed.myapp.customformfield;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.activiti.engine.form.AbstractFormType;

@Entity
@Table(name = "leave")
public class UserFormType extends AbstractFormType {

	@Override
	public String getName() {
		return "leaveEntity";
	}

	@Override
	public Object convertFormValueToModelValue(String value) {
		return value;
	}

	@Override
	public String convertModelValueToFormValue(Object model) {
		return model == null ? "" : model.toString();
	}

}
