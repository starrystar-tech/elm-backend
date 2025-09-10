package cn.softfocus.xkzy.module.iot.dal.dataobject.alert;

import cn.softfocus.xkzy.framework.common.enums.CommonStatusEnum;
import cn.softfocus.xkzy.framework.mybatis.core.dataobject.BaseDO;
import cn.softfocus.xkzy.framework.mybatis.core.type.IntegerListTypeHandler;
import cn.softfocus.xkzy.framework.mybatis.core.type.LongListTypeHandler;
import cn.softfocus.xkzy.module.iot.dal.dataobject.rule.IotSceneRuleDO;
import cn.softfocus.xkzy.module.iot.enums.DictTypeConstants;
import cn.softfocus.xkzy.module.iot.enums.alert.IotAlertReceiveTypeEnum;
import cn.softfocus.xkzy.module.system.api.user.dto.AdminUserRespDTO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * IoT 告警配置 DO
 *
 *
 */
@TableName(value = "iot_alert_config", autoResultMap = true)
@KeySequence("iot_alert_config_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotAlertConfigDO extends BaseDO {

    /**
     * 配置编号
     */
    @TableId
    private Long id;
    /**
     * 配置名称
     */
    private String name;
    /**
     * 配置描述
     */
    private String description;
    /**
     * 配置状态
     *
     * 字典 {@link DictTypeConstants#ALERT_LEVEL}
     */
    private Integer level;
    /**
     * 配置状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

    /**
     * 关联的场景联动规则编号数组
     *
     * 关联 {@link IotSceneRuleDO#getId()}
     */
    @TableField(typeHandler = LongListTypeHandler.class)
    private List<Long> sceneRuleIds;

    /**
     * 接收的用户编号数组
     *
     * 关联 {@link AdminUserRespDTO#getId()}
     */
    @TableField(typeHandler = LongListTypeHandler.class)
    private List<Long> receiveUserIds;
    /**
     * 接收的类型数组
     *
     * 枚举 {@link IotAlertReceiveTypeEnum}
     */
    @TableField(typeHandler = IntegerListTypeHandler.class)
    private List<Integer> receiveTypes;

}
