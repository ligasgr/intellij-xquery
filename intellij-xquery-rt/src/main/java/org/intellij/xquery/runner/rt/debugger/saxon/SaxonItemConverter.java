/*
 * Copyright 2013-2017 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.runner.rt.debugger.saxon;

import net.sf.saxon.ma.map.HashTrieMap;
import net.sf.saxon.om.Item;
import net.sf.saxon.om.Sequence;
import net.sf.saxon.om.SequenceIterator;
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.tree.iter.AtomicIterator;
import net.sf.saxon.tree.tiny.TinyNodeImpl;
import net.sf.saxon.tree.tiny.TinyParentNodeImpl;
import net.sf.saxon.tree.tiny.TinyTextImpl;
import net.sf.saxon.tree.tiny.TinyTree;
import net.sf.saxon.value.AtomicValue;

import java.util.ArrayList;
import java.util.List;

class SaxonItemConverter {

    public static String[] getSequenceValue(Sequence sequence) {
        if (sequence == null) return null;
        try {
            return getSequenceValue(sequence.iterate());
        } catch (XPathException e) {
            return null;
        }
    }

    public static String[] getSequenceValue(SequenceIterator iterator) {
        String type = "item()";
        try {
            List<String> sequenceItems = new ArrayList<>();
            Item next = iterator.next();
            if (next != null && next instanceof AtomicValue) {
                type = ((AtomicValue) next).getPrimitiveType().getPrimitiveItemType().toString();
            } else if (next != null && next instanceof HashTrieMap) {
                type = "map(*)";
            }

            while (next != null) {
                sequenceItems.add(itemToString(next));
                next = iterator.next();
            }

            StringBuilder sb = new StringBuilder();
            int numberOfSequenceItems = sequenceItems.size();
            if (numberOfSequenceItems > 1) {
                sb.append("(");
            }
            for (int i = 0; i < numberOfSequenceItems; i++) {
                sb.append(sequenceItems.get(i));
                if (i < numberOfSequenceItems - 1) {
                    sb.append(", ");
                }
            }
            if (numberOfSequenceItems > 1) {
                sb.append(")");
                type = type + "*";
            }

            return new String[]{sb.toString(), type};
        } catch (XPathException e) {
            return null;
        }
    }

    private static String itemToString(Item next) {
        if (next instanceof TinyParentNodeImpl) {
            TinyNodeImpl tinyNode = (TinyNodeImpl) next;
            return itemToString(tinyNode, tinyNode.getTree().getNumberOfNodes());
        } else if (next instanceof HashTrieMap) {
            return mapToString((HashTrieMap) next);
        } else {
            return next.getStringValue();
        }
    }

    private static String mapToString(HashTrieMap map) {
        StringBuilder result = new StringBuilder();
        result.append("(");
        List<AtomicValue> keyList = getKeys(map);
        for (int i = 0; i < keyList.size(); i++) {
            AtomicValue key = keyList.get(i);
            Sequence value = map.get(key);
            String[] sequenceValue = getSequenceValue(value);
            result.append(itemToString(key));
            result.append("->");
            result.append(sequenceValue[0]);
            if (i < keyList.size() - 1) {
                result.append(", ");
            }
        }
        result.append(")");
        return result.toString();
    }

    private static List<AtomicValue> getKeys(HashTrieMap map) {
        List<AtomicValue> keyList = new ArrayList<>();
        AtomicIterator keys = map.keys();
        AtomicValue key;
        while ((key = keys.next()) != null) {
            keyList.add(key);
        }
        return keyList;
    }

    private static String itemToString(TinyNodeImpl currentItem, int checkInArrayUntilIndex) {
        StringBuilder itemAsString = new StringBuilder();
        String tagName = currentItem.getDisplayName();
        itemAsString.append("<");
        itemAsString.append(tagName);
        itemAsString.append(attributesToString(currentItem));
        List<String> childNodes = childNodesToString(currentItem, checkInArrayUntilIndex);
        if (childNodes.size() > 0) {
            itemAsString.append(">");
            for (String childNode : childNodes) {
                itemAsString.append(childNode);
            }
            itemAsString.append("</");
            itemAsString.append(tagName);
            itemAsString.append(">");
        } else {
            itemAsString.append("/>");
        }
        return itemAsString.toString();
    }

    private static String attributesToString(TinyNodeImpl currentItem) {
        List<String> attributeList = new ArrayList<>();
        TinyTree tree = currentItem.getTree();
        int currentNode = currentItem.getNodeNumber();
        for (int i = 0; i < tree.getNumberOfAttributes(); ++i) {
            if (tree.getAttributeParentArray()[i] == currentNode) {
                StringBuilder attribute = new StringBuilder();
                int nameCode = tree.getAttributeNameCodeArray()[i];
                attribute.append(tree.getNamePool().getDisplayName(nameCode));
                attribute.append("=");
                attribute.append("'");
                attribute.append(tree.getAttributeValueArray()[i]);
                attribute.append("'");
                attributeList.add(attribute.toString());
            }
        }
        StringBuilder attributes = new StringBuilder();
        for (String anAttributeList : attributeList) {
            attributes.append(" ");
            attributes.append(anAttributeList);
        }
        return attributes.toString();
    }

    private static List<String> childNodesToString(TinyNodeImpl tinyTreeNode, int checkInArrayUntilIndex) {
        List<TinyNodeImpl> children = new ArrayList<>();
        List<String> childrenAsString = new ArrayList<>();
        TinyTree tree = tinyTreeNode.getTree();
        int currentNode = tinyTreeNode.getNodeNumber();

        for (int i = currentNode + 1; i < checkInArrayUntilIndex; i++) {
            if (getDepthOf(tree, i) < getDepthOf(tree, currentNode)) {
                break;
            }
            if (getDepthOf(tree, i) == (getDepthOf(tree, currentNode) + 1)) {
                if (tree.getNodeKind(i) == 1 || tree.getNodeKind(i) == 3) {
                    children.add(tree.getNode(i));
                }
            }
        }

        int childrenSize = children.size();
        for (int i = 0; i < childrenSize; i++) {
            TinyNodeImpl child = children.get(i);
            if (tree.getNodeKind(child.getNodeNumber()) == 3) {
                childrenAsString.add(TinyTextImpl.getStringValue(tree, child.getNodeNumber()).toString());
            } else if (tree.getNodeKind(child.getNodeNumber()) == 1) {
                int indexOfNext = (i + 1) >= childrenSize ? checkInArrayUntilIndex : children.get(i + 1).getNodeNumber();
                childrenAsString.add(itemToString(child, indexOfNext));
            }
        }

        return childrenAsString;
    }

    private static short getDepthOf(TinyTree tree, int i) {
        return tree.getNodeDepthArray()[i];
    }
}
