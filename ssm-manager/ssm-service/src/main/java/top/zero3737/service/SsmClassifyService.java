package top.zero3737.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.zero3737.dao.SsmClassifyMapper;
import top.zero3737.entity.SsmClassify;
import top.zero3737.entity.SsmClassifyExample;

@Service
public class SsmClassifyService {

	@Autowired
	private SsmClassifyMapper ssmClassifyMapper;
	
	public List<SsmClassify> findTreeData(Integer index) {
		
		SsmClassifyExample example = new SsmClassifyExample();
		
		example.createCriteria().andParentIdEqualTo(index);
		
		return ssmClassifyMapper.selectByExample(example);
		
	}
	
}
