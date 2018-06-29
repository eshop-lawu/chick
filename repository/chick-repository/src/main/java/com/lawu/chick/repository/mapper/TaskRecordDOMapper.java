package com.lawu.chick.repository.mapper;

import com.lawu.chick.repository.domain.TaskRecordDO;
import com.lawu.chick.repository.domain.TaskRecordDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TaskRecordDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_record
     *
     * @mbg.generated
     */
    long countByExample(TaskRecordDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_record
     *
     * @mbg.generated
     */
    int deleteByExample(TaskRecordDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_record
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_record
     *
     * @mbg.generated
     */
    int insert(TaskRecordDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_record
     *
     * @mbg.generated
     */
    int insertSelective(TaskRecordDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_record
     *
     * @mbg.generated
     */
    List<TaskRecordDO> selectByExampleWithRowbounds(TaskRecordDOExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_record
     *
     * @mbg.generated
     */
    List<TaskRecordDO> selectByExample(TaskRecordDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_record
     *
     * @mbg.generated
     */
    TaskRecordDO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_record
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TaskRecordDO record, @Param("example") TaskRecordDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_record
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TaskRecordDO record, @Param("example") TaskRecordDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_record
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TaskRecordDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_record
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TaskRecordDO record);
}