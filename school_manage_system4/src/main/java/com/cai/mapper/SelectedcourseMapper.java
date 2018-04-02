package com.cai.mapper;

import com.cai.po.Selectedcourse;
import com.cai.po.SelectedcourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SelectedcourseMapper {
    Integer countByExample(SelectedcourseExample example);

    int deleteByExample(SelectedcourseExample example);

    int insert(Selectedcourse record);

    int insertSelective(Selectedcourse record);

    List<Selectedcourse> selectByExample(SelectedcourseExample example);

    int updateByExampleSelective(@Param("record") Selectedcourse record, @Param("example") SelectedcourseExample example);

    int updateByExample(@Param("record") Selectedcourse record, @Param("example") SelectedcourseExample example);
}