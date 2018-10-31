/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.ballerinalang.util.codegen;

import org.ballerinalang.model.types.BType;
import org.ballerinalang.util.codegen.attributes.AttributeInfo;
import org.ballerinalang.util.codegen.attributes.AttributeInfoPool;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code ConstantInfo} represents a constant in a compiled package.
 *
 * @since 0.985.0
 */
public class ConstantInfo implements AttributeInfoPool {

    private int nameCPIndex;
    private String name;

//    private int typeCPIndex;
//    private int valueTypeTag;

//    private int globalMemIndex;
    private BType valueType;

    private Map<AttributeInfo.Kind, AttributeInfo> attributeInfoMap = new HashMap<>();

    public ConstantInfo(int nameCPIndex, String name ,/*int typeCPIndex*/ /*int valueTypeTag, int globalMemIndex,*/
                        BType valueType) {
        this.nameCPIndex = nameCPIndex;
        this.name = name;
//        this.typeCPIndex = typeCPIndex;
//        this.valueTypeTag = valueTypeTag;
//        this.globalMemIndex = globalMemIndex;
        this.valueType = valueType;
    }

    public int getNameCPIndex() {
        return nameCPIndex;
    }

    public String getName() {
        return name;
    }

//    public int getTypeCPIndex() {
//        return typeCPIndex;
//    }

    public BType getValueType() {
        return valueType;
    }

//    public int getValueTypeTag() {
//        return valueTypeTag;
//    }

    public AttributeInfo getAttributeInfo(AttributeInfo.Kind attributeKind) {
        return attributeInfoMap.get(attributeKind);
    }

    public void addAttributeInfo(AttributeInfo.Kind attributeKind, AttributeInfo attributeInfo) {
        attributeInfoMap.put(attributeKind, attributeInfo);
    }

//    public int getGlobalMemIndex() {
//        return globalMemIndex;
//    }

    public AttributeInfo[] getAttributeInfoEntries() {
        return attributeInfoMap.values().toArray(new AttributeInfo[0]);
    }
}
