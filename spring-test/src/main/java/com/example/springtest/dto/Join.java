package com.example.springtest.dto;


import com.example.springtest.enums.JoinTypeEnum;
import com.sun.istack.internal.NotNull;
import lombok.Data;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/10/31 16:58
 */
@Data
public class Join {



    //        @NotNull(message = "join_type 不允许为空")
//        private String joinType;
    private JoinTypeEnum joinType;


    private String on;





}

