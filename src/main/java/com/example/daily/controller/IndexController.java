package com.example.daily.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: zhiyuan
 * @date: 2018-04-25
 * @project: daily
 * @description:
 */

@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping(value = "test", method = { RequestMethod.POST })
	@ResponseBody
	public String home() {
		return "Hello this is for testing";
	}
}
