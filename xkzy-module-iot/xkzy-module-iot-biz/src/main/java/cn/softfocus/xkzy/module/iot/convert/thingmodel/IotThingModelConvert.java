package cn.softfocus.xkzy.module.iot.convert.thingmodel;

import cn.softfocus.xkzy.module.iot.dal.dataobject.thingmodel.model.ThingModelEvent;
import cn.softfocus.xkzy.module.iot.dal.dataobject.thingmodel.model.ThingModelProperty;
import cn.softfocus.xkzy.module.iot.dal.dataobject.thingmodel.model.ThingModelService;
import cn.softfocus.xkzy.module.iot.controller.admin.thingmodel.vo.IotThingModelSaveReqVO;
import cn.softfocus.xkzy.module.iot.dal.dataobject.thingmodel.IotThingModelDO;
import cn.softfocus.xkzy.module.iot.enums.thingmodel.IotThingModelTypeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

@Mapper
public interface IotThingModelConvert {

    IotThingModelConvert INSTANCE = Mappers.getMapper(IotThingModelConvert.class);

    @Mapping(target = "property", expression = "java(convertToProperty(bean))")
    @Mapping(target = "event", expression = "java(convertToEvent(bean))")
    @Mapping(target = "service", expression = "java(convertToService(bean))")
    IotThingModelDO convert(IotThingModelSaveReqVO bean);

    @Named("convertToProperty")
    default ThingModelProperty convertToProperty(IotThingModelSaveReqVO bean) {
        if (Objects.equals(bean.getType(), IotThingModelTypeEnum.PROPERTY.getType())) {
            return bean.getProperty();
        }
        return null;
    }

    @Named("convertToEvent")
    default ThingModelEvent convertToEvent(IotThingModelSaveReqVO bean) {
        if (Objects.equals(bean.getType(), IotThingModelTypeEnum.EVENT.getType())) {
            return bean.getEvent();
        }
        return null;
    }

    @Named("convertToService")
    default ThingModelService convertToService(IotThingModelSaveReqVO bean) {
        if (Objects.equals(bean.getType(), IotThingModelTypeEnum.SERVICE.getType())) {
            return bean.getService();
        }
        return null;
    }

}
