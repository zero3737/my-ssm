package top.zero3737.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import top.zero3737.service.SsmClassifyTreeService;

@Controller
@RequestMapping("/classifyTree")
public class ClassifyTreeServlet {

	@Autowired
	SsmClassifyTreeService ssmClassifyTreeService;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public String getClassifyList() {
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		hashMap.put("tree", ssmClassifyTreeService.findTreeData(0));
		hashMap.put("msg", "³É¹¦");
		hashMap.put("code", 1);
		
		return JSON.toJSONString(hashMap);
		
	}
	
}
