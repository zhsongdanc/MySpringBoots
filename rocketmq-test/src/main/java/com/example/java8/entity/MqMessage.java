package com.example.java8.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/4/11 19:45
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MqMessage implements Serializable {

    private String name;

    private String msg;

}
