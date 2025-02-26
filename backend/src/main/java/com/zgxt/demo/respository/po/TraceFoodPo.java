package com.zgxt.demo.respository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value ="trace_food")
public class TraceFoodPo implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String rolemodel;

    private String tracenumber;

    private Date date;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}
