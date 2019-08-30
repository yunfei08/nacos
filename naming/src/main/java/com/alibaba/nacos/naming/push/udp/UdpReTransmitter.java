/*
 * Copyright (C) 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.nacos.naming.push.udp;

import com.alibaba.nacos.api.naming.push.AckEntry;
import com.alibaba.nacos.naming.misc.Loggers;
import com.alibaba.nacos.naming.push.PushService;
import com.alibaba.nacos.naming.push.AbstractReTransmitter;

/**
 * @author pbting
 * @date 2019-08-28 9:30 AM
 */
public class UdpReTransmitter extends AbstractReTransmitter {

    private UdpEmitterService udpEmitter;

    public UdpReTransmitter(PushService pushService, AckEntry ackEntry, UdpEmitterService udpEmitter) {
        super(pushService, ackEntry);
        this.udpEmitter = udpEmitter;
    }

    @Override
    public void reTransmitter(PushService pushService) {
        Loggers.PUSH.info("retry to push data, key: " + ackEntry.getKey());
        udpEmitter.udpPush(ackEntry);
    }
}