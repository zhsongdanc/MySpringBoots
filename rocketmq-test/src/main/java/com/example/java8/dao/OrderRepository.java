package com.example.java8.dao;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/4/11 16:18
 */

import com.example.java8.entity.GoodsOrder;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<GoodsOrder, Integer> {

    List<GoodsOrder> findAll();

    <S extends GoodsOrder> S save(S entity);
}
