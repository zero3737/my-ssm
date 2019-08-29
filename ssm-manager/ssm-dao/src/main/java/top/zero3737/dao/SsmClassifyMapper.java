package top.zero3737.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.zero3737.entity.SsmClassify;
import top.zero3737.entity.SsmClassifyExample;

public interface SsmClassifyMapper {
    long countByExample(SsmClassifyExample example);

    int deleteByExample(SsmClassifyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SsmClassify record);

    int insertSelective(SsmClassify record);

    List<SsmClassify> selectByExample(SsmClassifyExample example);

    SsmClassify selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SsmClassify record, @Param("example") SsmClassifyExample example);

    int updateByExample(@Param("record") SsmClassify record, @Param("example") SsmClassifyExample example);

    int updateByPrimaryKeySelective(SsmClassify record);

    int updateByPrimaryKey(SsmClassify record);
}