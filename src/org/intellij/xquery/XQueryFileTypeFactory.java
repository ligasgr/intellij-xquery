package org.intellij.xquery;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;


/**
 * User: ligasgr
 * Date: 10/02/13
 * Time: 17:48
 */
public class XQueryFileTypeFactory  extends FileTypeFactory {
    public void createFileTypes(@NotNull FileTypeConsumer consumer) {
        consumer.consume(XQueryFileType.INSTANCE, XQueryFileType.DEFAULT_EXTENSION);
    }
}
