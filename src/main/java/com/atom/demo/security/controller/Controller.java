/*
 * @Project Name: security
 * @File Name: Controller.java
 * @Package Name: com.atom.demo.security.controller
 * @Date: 2021年5月18日上午11:13:30
 * @Creator: xuchuandi
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.atom.demo.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * TODO
 * @author xuchuandi
 * @date 2021年5月18日上午11:13:30
 * @see
 */
@Api(tags = "测试")
@RestController
public class Controller {
	
	@ApiOperation("hello")
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
}
