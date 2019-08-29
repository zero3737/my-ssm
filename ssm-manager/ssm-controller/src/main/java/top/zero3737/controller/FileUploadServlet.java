package top.zero3737.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadServlet {

	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public String returnPageData(@RequestParam("file")MultipartFile[] files) {
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		try {
			
			for (MultipartFile file : files) {
				
				// 获取时间
				SimpleDateFormat sdf = new SimpleDateFormat();
				sdf.applyPattern("/yyyy/MM/dd");
				String format = sdf.format(new Date());
				// 获取文件名
				String originalFilename = file.getOriginalFilename();
				// 以时间戳作为文件名
				String fileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
				// 获取保存路径
				String realPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/images" + format);
				File _file = new File(realPath + "/" + fileName);
				// 文件路径不存在在创建
				if(!_file.exists()) {
					
					_file.mkdirs();
					
				}
				file.transferTo(_file);
				
			}
			hashMap.put("msg", "上传成功");
			hashMap.put("code", 1);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			hashMap.put("msg", "上传失败");
			hashMap.put("code", 0);
			
		}
		
		return JSON.toJSONString(hashMap);
		
	}
	
}
