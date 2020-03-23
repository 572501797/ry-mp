package com.ruoyi.framework.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;


@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
@MapperScan("com.ruoyi.**.mapper*")
public class MybatisPlusConfig {


    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }



    @Bean
    public MetaObjectHandler metaObjectHandler() {

        MetaObjectHandler metaObjectHandler = new MetaObjectHandler() {


            /**
             * 新增填充
             */
            @Override
            public void insertFill(MetaObject metaObject) {
                String loginName = ShiroUtils.getSysUser().getUserName();
                this.setFieldValByName("createTime" , DateUtils.getNowDate(), metaObject);
                this.setFieldValByName("createBy" , loginName, metaObject);
            }

            /**
             * 更新填充
             */
            @Override
            public void updateFill(MetaObject metaObject) {

                String loginName = ShiroUtils.getSysUser().getUserName();
                this.setFieldValByName("updateBy" , loginName, metaObject);
                this.setFieldValByName("updateTime" , DateUtils.getNowDate(), metaObject);


            }
        };
        return metaObjectHandler;
    }

}
