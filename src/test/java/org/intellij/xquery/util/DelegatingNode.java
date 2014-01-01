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

package org.intellij.xquery.util;

import org.w3c.dom.*;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * User: ligasgr
 * Date: 27/09/13
 * Time: 15:42
 */
public class DelegatingNode implements Document {

    private final Document originalDocument;

    public DelegatingNode(Document originalDocument) {
        this.originalDocument = originalDocument;
    }

    @Override
    public Node cloneNode(boolean b) {
        return originalDocument.cloneNode(b);
    }

    @Override
    public void normalize() {
        originalDocument.normalize();
    }

    @Override
    public boolean isSupported(String feature, String version) {
        return originalDocument.isSupported(feature, version);
    }

    @Override
    public String getNamespaceURI() {
        return originalDocument.getNamespaceURI();
    }

    @Override
    public String getPrefix() {
        return originalDocument.getPrefix();
    }

    @Override
    public void setPrefix(String prefix) throws DOMException {
        originalDocument.setPrefix(prefix);
    }

    @Override
    public String getLocalName() {
        return originalDocument.getLocalName();
    }

    @Override
    public boolean hasAttributes() {
        return originalDocument.hasAttributes();
    }

    @Override
    public String getBaseURI() {
        return originalDocument.getBaseURI();
    }

    @Override
    public short compareDocumentPosition(Node other) throws DOMException {
        return originalDocument.compareDocumentPosition(other);
    }

    @Override
    public short getNodeType() {
        return originalDocument.getNodeType();
    }

    @Override
    public Node getParentNode() {
        return originalDocument.getParentNode();
    }

    @Override
    public NodeList getChildNodes() {
        return originalDocument.getChildNodes();
    }

    @Override
    public Node getFirstChild() {
        return originalDocument.getFirstChild();
    }

    @Override
    public Node getLastChild() {
        return originalDocument.getLastChild();
    }

    @Override
    public Node getPreviousSibling() {
        return originalDocument.getPreviousSibling();
    }

    @Override
    public Node getNextSibling() {
        return originalDocument.getNextSibling();
    }

    @Override
    public NamedNodeMap getAttributes() {
        return originalDocument.getAttributes();
    }

    @Override
    public Document getOwnerDocument() {
        return originalDocument.getOwnerDocument();
    }

    @Override
    public String getNodeName() {
        return originalDocument.getNodeName();
    }

    @Override
    public String getNodeValue() throws DOMException {
        return originalDocument.getNodeValue();
    }

    @Override
    public void setNodeValue(String nodeValue) throws DOMException {
        originalDocument.setNodeValue(nodeValue);
    }

    @Override
    public Node insertBefore(Node node, Node node2) throws DOMException {
        return originalDocument.insertBefore(node, node2);
    }

    @Override
    public Node removeChild(Node node) throws DOMException {
        return originalDocument.removeChild(node);
    }

    @Override
    public Node appendChild(Node newChild) throws DOMException {
        return originalDocument.appendChild(newChild);
    }

    @Override
    public boolean hasChildNodes() {
        return originalDocument.hasChildNodes();
    }

    @Override
    public Node replaceChild(Node node, Node node2) throws DOMException {
        return originalDocument.replaceChild(node, node2);
    }

    @Override
    public String getTextContent() throws DOMException {
        return originalDocument.getTextContent();
    }

    @Override
    public void setTextContent(String s) throws DOMException {
        originalDocument.setTextContent(s);
    }

    @Override
    public boolean isSameNode(Node other) {
        return originalDocument.isSameNode(other);
    }

    @Override
    public String lookupPrefix(String namespaceURI) {
        return originalDocument.lookupPrefix(namespaceURI);
    }

    @Override
    public boolean isDefaultNamespace(String namespaceURI) {
        return originalDocument.isDefaultNamespace(namespaceURI);
    }

    @Override
    public String lookupNamespaceURI(String prefix) {
        return originalDocument.lookupNamespaceURI(prefix);
    }

    @Override
    public boolean isEqualNode(Node arg) {
        return originalDocument.isEqualNode(arg);
    }

    @Override
    public Object getFeature(String s, String s2) {
        return originalDocument.getFeature(s, s2);
    }

    @Override
    public Object setUserData(String key, Object data, UserDataHandler handler) {
        return originalDocument.setUserData(key, data, handler);
    }

    @Override
    public Object getUserData(String key) {
        return originalDocument.getUserData(key);
    }

    @Override
    public String toString() {
        DOMSource domSource = new DOMSource(originalDocument);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return originalDocument.toString();
    }

    @Override
    public DocumentType getDoctype() {
        return originalDocument.getDoctype();
    }

    @Override
    public DOMImplementation getImplementation() {
        return originalDocument.getImplementation();
    }

    @Override
    public Element getDocumentElement() {
        return originalDocument.getDocumentElement();
    }

    @Override
    public Element createElement(String tagName) throws DOMException {
        return originalDocument.createElement(tagName);
    }

    @Override
    public DocumentFragment createDocumentFragment() {
        return originalDocument.createDocumentFragment();
    }

    @Override
    public Text createTextNode(String data) {
        return originalDocument.createTextNode(data);
    }

    @Override
    public Comment createComment(String data) {
        return originalDocument.createComment(data);
    }

    @Override
    public CDATASection createCDATASection(String data) throws DOMException {
        return originalDocument.createCDATASection(data);
    }

    @Override
    public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
        return originalDocument.createProcessingInstruction(target, data);
    }

    @Override
    public Attr createAttribute(String name) throws DOMException {
        return originalDocument.createAttribute(name);
    }

    @Override
    public EntityReference createEntityReference(String name) throws DOMException {
        return originalDocument.createEntityReference(name);
    }

    @Override
    public NodeList getElementsByTagName(String tagname) {
        return originalDocument.getElementsByTagName(tagname);
    }

    @Override
    public Node importNode(Node importedNode, boolean deep) throws DOMException {
        return originalDocument.importNode(importedNode, deep);
    }

    @Override
    public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
        return originalDocument.createElementNS(namespaceURI, qualifiedName);
    }

    @Override
    public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
        return originalDocument.createAttributeNS(namespaceURI, qualifiedName);
    }

    @Override
    public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
        return originalDocument.getElementsByTagNameNS(namespaceURI, localName);
    }

    @Override
    public Element getElementById(String elementId) {
        return originalDocument.getElementById(elementId);
    }

    @Override
    public String getInputEncoding() {
        return originalDocument.getInputEncoding();
    }

    @Override
    public String getXmlEncoding() {
        return originalDocument.getXmlEncoding();
    }

    @Override
    public boolean getXmlStandalone() {
        return originalDocument.getXmlStandalone();
    }

    @Override
    public void setXmlStandalone(boolean xmlStandalone) throws DOMException {
        originalDocument.setXmlStandalone(xmlStandalone);
    }

    @Override
    public String getXmlVersion() {
        return originalDocument.getXmlVersion();
    }

    @Override
    public void setXmlVersion(String xmlVersion) throws DOMException {
        originalDocument.setXmlVersion(xmlVersion);
    }

    @Override
    public boolean getStrictErrorChecking() {
        return originalDocument.getStrictErrorChecking();
    }

    @Override
    public void setStrictErrorChecking(boolean strictErrorChecking) {
        originalDocument.setStrictErrorChecking(strictErrorChecking);
    }

    @Override
    public String getDocumentURI() {
        return originalDocument.getDocumentURI();
    }

    @Override
    public void setDocumentURI(String documentURI) {
        originalDocument.setDocumentURI(documentURI);
    }

    @Override
    public Node adoptNode(Node source) throws DOMException {
        return originalDocument.adoptNode(source);
    }

    @Override
    public DOMConfiguration getDomConfig() {
        return originalDocument.getDomConfig();
    }

    @Override
    public void normalizeDocument() {
        originalDocument.normalizeDocument();
    }

    @Override
    public Node renameNode(Node n, String namespaceURI, String qualifiedName) throws DOMException {
        return originalDocument.renameNode(n, namespaceURI, qualifiedName);
    }
}
