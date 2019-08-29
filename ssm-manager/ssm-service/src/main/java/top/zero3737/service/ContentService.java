package top.zero3737.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.zero3737.dao.SsmContentMapper;
import top.zero3737.entity.SsmContent;
import top.zero3737.entity.SsmContentExample;

@Service
public class ContentService {

	@Autowired
	private SsmContentMapper ssmContentMapper;
	
	public int insertContent(String htmlContent) {
		
		SsmContent ssmContent = new SsmContent();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			
			Date parse = sdf.parse(sdf.format(new Date()));
			ssmContent.setUpdateTime(parse);
			ssmContent.setCreateTime(parse);
			
		} catch (ParseException e) {
			
			e.printStackTrace();
			
		}
		ssmContent.setContent(htmlContent);
		ssmContent.setId(null);
		
		return ssmContentMapper.insert(ssmContent);
		
	}
	
	public SsmContent selectLastContent() {
		
		SsmContentExample ssmContentExample = new SsmContentExample();
		
		ssmContentExample.setOrderByClause("id desc");
		List<SsmContent> selectByExample = ssmContentMapper.selectByExample(ssmContentExample);
		
		return selectByExample.get(0);
		
	}
	
}
