package cn.softfocus.xkzy.module.iot.core.messagebus.core.local;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IotLocalMessage {

    private String topic;

    private Object message;

}
