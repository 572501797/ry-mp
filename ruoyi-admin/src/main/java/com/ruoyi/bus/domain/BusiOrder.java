package com.ruoyi.bus.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
                                                            
/**
 * 接送订单管理表 busi_order
 *
 * @author ruoyi
 * @date 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("busi_order")
public class BusiOrder  extends BaseEntity implements Serializable
        {
private static final long serialVersionUID = 1L;

/** ID */
private Long id;
/** 订单号 */
private String orderNo;
/** 下单人userid */
private String orderUserId;
/** 联系电话 */
private String orderUserPhone;
/** 订单状态：101 已提交  102 待接送 103 已接送 */
private String orderStatus;
/** 接送时间 */
private Date orderCreateTime;
/** 订单金额 */
private Double orderPrice;
/** 下单人姓名 */
private String orderUserName;
/** 要接送的宝宝唯一编号 */
private String orderMentId;
/** 宝宝名称 */
private String orderMentName;

}