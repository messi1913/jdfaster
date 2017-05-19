package com.jdfaster.idgen.impl;

import java.util.Map;
import java.util.UUID;

import com.jdfaster.idgen.IdGen;
import com.jdfaster.idgen.IdRule;

public class IdGenImpl implements IdGen {

	@Override
	public IdRule getIdRule(String idRuleCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveIdRule(IdRule idRule) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String genId(String idRuleCode, Map<String, String> params) throws Exception {
		return UUID.randomUUID().toString();
	}

}
