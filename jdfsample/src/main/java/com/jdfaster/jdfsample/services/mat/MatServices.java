package com.jdfaster.jdfsample.services.mat;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jdfaster.jdfsample.services.mat.get_comp_list.GetMatCompList;
import com.jdfaster.jdfsample.services.mat.get_comp_list.GetMatCompListIn;
import com.jdfaster.jdfsample.services.mat.get_comp_list.GetMatCompListOut;

@RestController
@RequestMapping("/services/mat/")
public class MatServices {

	@RequestMapping(method = RequestMethod.GET, path = "/get_comp_list/")
	public GetMatCompListOut getMatCompList(@RequestBody GetMatCompListIn input) throws Exception {
		return new GetMatCompList().getMatCompList(input);
	}

}
