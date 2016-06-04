package com.jdfaster.jdfsample.services.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdfaster.jdfsample.services.user.create.Create;
import com.jdfaster.jdfsample.services.user.create.CreateInVo;
import com.jdfaster.jdfsample.services.user.create.CreateOutVo;
import com.jdfaster.jdfsample.utils.SvcUtils;

@Controller
public class UserServices implements ApplicationContextAware {

	@RequestMapping(value = "/services/user/create")
	public String create(HttpServletRequest request) throws Exception {
		CreateInVo input = SvcUtils.toInVo(request);
		CreateOutVo output = ac.getBean(UserServices.class).create(input);
		return SvcUtils.toOutStr(output);
	}

	public CreateOutVo create(CreateInVo input) throws Exception {
		return new Create().create(input);
	}

	@RequestMapping(value = "/services/user/update")
	public String update(HttpServletRequest request) throws Exception {
		// TODO
		return null;
	}

	@RequestMapping(value = "/services/user/delete")
	public String delete(HttpServletRequest request) throws Exception {
		// TODO
		return null;
	}

	public ApplicationContext ac;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ac = applicationContext;
	}

}
