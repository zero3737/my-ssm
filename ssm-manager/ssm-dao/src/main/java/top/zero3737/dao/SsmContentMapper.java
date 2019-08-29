package top.zero3737.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.zero3737.entity.SsmContent;
import top.zero3737.entity.SsmContentExample;

public interface SsmContentMapper {
    long countByExample(SsmContentExample example);

    int deleteByExample(SsmContentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SsmContent record);

    int insertSelective(SsmContent record);

    List<SsmContent> selectByExample(SsmContentExample example);

    SsmContent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SsmContent record, @Param("example") SsmContentExample example);

    int updateByExample(@Param("record") SsmContent record, @Param("example") SsmContentExample example);

    int updateByPrimaryKeySelective(SsmContent record);

    int updateByPrimaryKey(SsmContent record);
}