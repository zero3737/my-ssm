package top.zero3737.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import top.zero3737.dao.SsmUserMapper;
import top.zero3737.entity.SsmUser;
import top.zero3737.entity.SsmUserExample;

@Service
public class SsmUserService {
	
	@Autowired
	private SsmUserMapper ssmUserMapper;
	private SsmUserExample example;

	public PageInfo findPageData(int pageNum, int pageSize) {
		
		example = new SsmUserExample();
		PageHelper.startPage(pageNum, pageSize);
		List<SsmUser> selectByExample = ssmUserMapper.selectByExample(example);
		PageInfo pageInfo = new PageInfo(selectByExample);
		
		return pageInfo;
		
	}
	
}
