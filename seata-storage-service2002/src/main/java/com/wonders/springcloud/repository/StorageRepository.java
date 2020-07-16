package com.wonders.springcloud.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author qiu
 * @date 2020-07-16 14:27
 */
@Repository
@Mapper
public interface StorageRepository {

    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
