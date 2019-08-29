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
			
			// ÿ��ѭ��ʵ����һ�� hashMap ����
			hashMap = new HashMap<String, Object>();
			Integer id = ssmClassify.getId();
			hashMap.put("name", ssmClassify.getName());
			hashMap.put("id", id);
			// �ýڵ㲻�Ǹ��ڵ���Ϊ�ݹ����
			if(ssmClassify.getIsParentId() == 1) {
				
				// ÿ��ѭ���������Ӽ���
				childrens = new ArrayList<HashMap<String, Object>>();
				// �ݹ����
				List<HashMap<String, Object>> findTreeData = findTreeData(id);
				// �������ӽ����뺢�Ӽ�����
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
