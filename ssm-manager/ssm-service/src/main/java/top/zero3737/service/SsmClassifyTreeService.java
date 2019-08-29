package top.zero3737.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.zero3737.dao.SsmClassifyMapper;
import top.zero3737.entity.SsmClassify;
import top.zero3737.entity.SsmClassifyExample;

@Service
public class SsmClassifyTreeService {

	@Autowired
	SsmClassifyMapper ssmClassifyMapper;
	
	public List<HashMap<String, Object>> findTreeData(int parentId) {

		ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
		ArrayList<HashMap<String, Object>> childrens;
		HashMap<String, Object> hashMap;
		SsmClassifyExample ssmClassifyExample = new SsmClassifyExample();
		
		ssmClassifyExample.createCriteria().andParentIdEqualTo(parentId);
		List<SsmClassify> selectByExample = ssmClassifyMapper.selectByExample(ssmClassifyExample);
		for (SsmClassify ssmClassify : selectByExample) {
			
			// 每次循环实例化一个 hashMap 对象
			hashMap = new HashMap<String, Object>();
			Integer id = ssmClassify.getId();
			hashMap.put("name", ssmClassify.getName());
			hashMap.put("id", id);
			// 该节点不是父节点则为递归出口
			if(ssmClassify.getIsParentId() == 1) {
				
				// 每次循环创建孩子集合
				childrens = new ArrayList<HashMap<String, Object>>();
				// 递归入口
				List<HashMap<String, Object>> findTreeData = findTreeData(id);
				// 将所有子结点放入孩子集合中
				for (HashMap<String, Object> Node : findTreeData) {
					
					childrens.add(Node);
					
				}
				hashMap.put("childrens", childrens);
				
			}
			arrayList.add(hashMap);
			
		}
		
		return arrayList;
		
	}
	
}
