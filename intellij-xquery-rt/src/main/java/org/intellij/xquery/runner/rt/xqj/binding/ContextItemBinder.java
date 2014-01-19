/*
 * Copyright 2013-2014 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
 * (see the CONTRIBUTORS file).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.intellij.xquery.runner.rt.xqj.binding;

import org.intellij.xquery.runner.rt.FileUtil;
import org.intellij.xquery.runner.rt.XQueryRunConfig;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQConstants;
import javax.xml.xquery.XQPreparedExpression;
import java.io.IOException;

/**
 * User: ligasgr
 * Date: 04/11/13
 * Time: 18:22
 */
public class ContextItemBinder {

    private final TypeBinderFactory binderFactory;
    private final XQueryRunConfig config;

    public ContextItemBinder(TypeBinderFactory binderFactory, XQueryRunConfig config) {
        this.binderFactory = binderFactory;
        this.config = config;
    }

    public void bindContextItem(XQConnection connection, XQPreparedExpression preparedExpression) throws Exception {
        if (config.isContextItemEnabled()) {
            String contextItemValue = config.isContextItemFromEditorEnabled() ? config.getContextItemText() :
                    readFile(config.getContextItemFile());
            binderFactory.getBinder(config.getContextItemType()).bind(preparedExpression, connection,
                    XQConstants.CONTEXT_ITEM, contextItemValue,
                    config.getContextItemType());
        }
    }

    protected String readFile(String fileName) throws IOException {
        return FileUtil.readFile(fileName);
    }
}
