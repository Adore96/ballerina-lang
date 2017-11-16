/*
*  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

package org.ballerinalang.net.uri;

import org.ballerinalang.net.uri.parser.DataElement;
import org.ballerinalang.net.uri.parser.DataElementCreator;
import org.ballerinalang.net.uri.parser.Node;
import org.ballerinalang.net.uri.parser.URITemplateParser;

import java.util.Map;

/**
 * Basic URI Template implementation.
 *
 * @param <DataType> Data type stored in the data element.
 * @param <CheckerType> Additional checker for data element.
 **/
public class URITemplate<DataType, CheckerType> {

    private Node<DataElement<DataType, CheckerType>> syntaxTree;

    public URITemplate(Node<DataElement<DataType, CheckerType>> syntaxTree) {
        this.syntaxTree = syntaxTree;
    }

    public String expand(Map<String, String> variables) {
        return null;
    }

    public DataType matches(String uri, Map<String, String> variables, CheckerType checker) {
        DataElement<DataType, CheckerType> dataElement = syntaxTree.matchAll(uri, variables, 0);
        if (dataElement == null) {
            return null;
        }
        return dataElement.getData(checker);
    }

    public void parse(String uriTemplate, DataType resource,
                      DataElementCreator<? extends DataElement<DataType, CheckerType>>
                              elementCreator) throws URITemplateException {
        uriTemplate = removeTheFirstAndLastBackSlash(uriTemplate);

        URITemplateParser<DataType, CheckerType> parser = new URITemplateParser<>(syntaxTree, elementCreator);
        parser.parse(uriTemplate, resource);
    }

    public String removeTheFirstAndLastBackSlash(String template) throws URITemplateException {
        String uri = template;
        if ("/".equals(uri)) {
            return uri;
        }
        if (uri.startsWith("*")) {
            throw new URITemplateException("Invalid path literal");
        }
        if (!"/".equals(uri) && uri.endsWith("/")) {
            uri = uri.substring(0, uri.length() - 1);
        }
        if (uri.startsWith("/")) {
            uri = uri.substring(1);
        }
        return uri;
    }
}
