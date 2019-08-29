package top.zero3737.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import top.zero3737.entity.SsmContent;
import top.zero3737.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentServlet {
	
	@Autowired
	ContentService contentService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public String addContent(@RequestBody String htmlContent) {
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		// 插入数据
		contentService.insertContent(htmlContent);
		// 返回数据库最后一条数据
		SsmContent selectLastContent = contentService.selectLastContent();
		hashMap.put("code", 1);
		hashMap.put("msg", "提交成功");
		hashMap.put("bean", selectLastContent);
		
		return JSON.toJSONString(hashMap);
		
	}
	
}
