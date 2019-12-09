package com.example.shiro.cms.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * FooBar
 * </p>
 *
 * @author jack
 * @since 2019-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FooBar implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * Foo
     */
    private String foo;

    /**
     * Bar
     */
    private String bar;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态，0：禁用，1：启用
     */
    private Integer state;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


}
