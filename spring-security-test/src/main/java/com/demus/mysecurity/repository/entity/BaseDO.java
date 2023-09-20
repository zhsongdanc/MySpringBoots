package com.demus.mysecurity.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

/**
 * @Desc:
 * @Date: 3:50 下午 2022/5/23
 * @Author nebulaliao
 */
@Data
public class BaseDO implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Instant createTime;
    private Instant updateTime;
}
