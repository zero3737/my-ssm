package top.zero3737.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.zero3737.entity.SsmUser;
import top.zero3737.entity.SsmUserExample;

public interface SsmUserMapper {
    long countByExample(SsmUserExample example);

    int deleteByExample(SsmUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(SsmUser record);

    int insertSelective(SsmUser record);

    List<SsmUser> selectByExample(SsmUserExample example);

    SsmUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SsmUser record, @Param("example") SsmUserExample example);

    int updateByExample(@Param("record") SsmUser record, @Param("example") SsmUserExample example);

    int updateByPrimaryKeySelective(SsmUser record);

    int updateByPrimaryKey(SsmUser record);
}