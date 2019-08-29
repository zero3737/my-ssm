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
				
				// ��ȡʱ��
				SimpleDateFormat sdf = new SimpleDateFormat();
				sdf.applyPattern("/yyyy/MM/dd");
				String format = sdf.format(new Date());
				// ��ȡ�ļ���
				String originalFilename = file.getOriginalFilename();
				// ��ʱ�����Ϊ�ļ���
				String fileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
				// ��ȡ����·��
				String realPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/images" + format);
				File _file = new File(realPath + "/" + fileName);
				// �ļ�·���������ڴ���
				if(!_file.exists()) {
					
					_file.mkdirs();
					
				}
				file.transferTo(_file);
				
			}
			hashMap.put("msg", "�ϴ��ɹ�");
			hashMap.put("code", 1);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			hashMap.put("msg", "�ϴ�ʧ��");
			hashMap.put("code", 0);
			
		}
		
		return JSON.toJSONString(hashMap);
		
	}
	
}
