package com.jdfaster.idgen;

import java.util.Map;

public interface IdGen {
	IdRule getIdRule(String idRuleCode) throws Exception;

	void saveIdRule(IdRule idRule) throws Exception;

	String genId(String idRuleCode, Map<String, String> params) throws Exception;
}
