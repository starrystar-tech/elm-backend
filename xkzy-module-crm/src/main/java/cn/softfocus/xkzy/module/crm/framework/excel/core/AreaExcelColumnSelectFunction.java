package cn.softfocus.xkzy.module.crm.framework.excel.core;

import cn.softfocus.xkzy.framework.excel.core.function.ExcelColumnSelectFunction;
import cn.softfocus.xkzy.framework.ip.core.Area;
import cn.softfocus.xkzy.framework.ip.core.utils.AreaUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区下拉框数据源的 {@link ExcelColumnSelectFunction} 实现类
 *
 * 三
 */
@Service
public class AreaExcelColumnSelectFunction implements ExcelColumnSelectFunction {

    public static final String NAME = "getCrmAreaNameList"; // 防止和别的模块重名

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public List<String> getOptions() {
        // 获取地区下拉数据
        Area area = AreaUtils.getArea(Area.ID_CHINA);
        return AreaUtils.getAreaNodePathList(area.getChildren());
    }

}
