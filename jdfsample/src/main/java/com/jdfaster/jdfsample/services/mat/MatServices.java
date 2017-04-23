package com.jdfaster.jdfsample.services.mat;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jdfaster.jdfsample.services.mat.view_comp_list.ViewMatCompList;
import com.jdfaster.jdfsample.services.mat.view_comp_list.ViewMatCompListIn;
import com.jdfaster.jdfsample.services.mat.view_comp_list.ViewMatCompListOut;

@RestController
@RequestMapping("/services/mat/")
public class MatServices {

	@RequestMapping(method = RequestMethod.GET, path = "/view_comp_list/")
	public ViewMatCompListOut viewMatCompList(@RequestBody ViewMatCompListIn input) throws Exception {
		return new ViewMatCompList().viewMatCompList(input);
	}

}
