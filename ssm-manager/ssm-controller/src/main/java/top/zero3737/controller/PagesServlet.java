package top.zero3737.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import top.zero3737.service.SsmUserService;

@Controller
@RequestMapping("/pages")
public class PagesServlet {

	@Autowired
	private SsmUserService ssmUserService;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public String returnPageData(@RequestParam(defaultValue = "0")Integer page) {
		
		return JSON.toJSONString(ssmUserService.findPageData(page, 2));
		
	}
	
}
