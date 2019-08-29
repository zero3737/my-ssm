package top.zero3737.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import top.zero3737.service.SsmClassifyService;

@Controller
@RequestMapping("/classify")
public class ClassifyServlet {
	
	@Autowired
	SsmClassifyService ssmClassifyService;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public String getClassifyList(@RequestParam(defaultValue = "0")Integer index) {
		
		return JSON.toJSONString(ssmClassifyService.findTreeData(index));
		
	}
}
