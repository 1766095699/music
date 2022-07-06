package com.music.musiccommon.bean;

import com.baomidou.mybatisplus.annotation.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author testjava
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order")
public class Order implements Serializable {
//    MyRabbitMqConfig
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

   
    private String orderNo;

   
    private String courseId;

    
    private String courseTitle;

   
    private String courseCover;

  
    private String teacherName;
 
    private String memberId;

  
    private String nickname;

    
    private String mobile;

    //@ApiModelProperty(value = "订单金额（分）")
    private BigDecimal totalFee;

    //@ApiModelProperty(value = "支付类型（1：微信 2：支付宝）")
    private Integer payType;

    //@ApiModelProperty(value = "订单状态（0：未支付 1：已支付）")
    private Integer status;

    //@ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    //@ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    //@ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
