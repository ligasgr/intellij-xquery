/*
 * Copyright 2013-2016 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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

package org.intellij.xquery.reference.module;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import org.intellij.xquery.XQueryFileType;
import org.intellij.xquery.BaseFunctionalTestCase;
import org.intellij.xquery.psi.XQueryFile;
import org.intellij.xquery.psi.XQueryModuleImportPath;

import static org.intellij.xquery.Assertions.assertChildOf;
import static org.intellij.xquery.reference.ReferenceUtil.getTargetOfReferenceAtCaret;

/**
 * User: ligasgr
 * Date: 02/07/13
 * Time: 21:00
 */
public class XQueryModuleReferenceTest extends BaseFunctionalTestCase {
    @Override
    protected String getTestDataPath() {
        return "src/testFunctional/testData/org/intellij/xquery/reference/module";
    }

    public void testModuleReference() {
        myFixture.configureByFiles("ModuleReference.xq", "ModuleReference_ReferencedModule.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryModuleImportPath.class);

        assertChildOf(resolvedReference, XQueryFile.class);
        assertEquals("ModuleReference_ReferencedModule.xq", ((XQueryFile) resolvedReference).getName());
    }

    public void testRenameOfTheFileWithReference() {
        myFixture.configureByFiles("ModuleReference.xq", "ModuleReference_ReferencedModule.xq");
        PsiFile[] files = FilenameIndex.getFilesByName(myFixture.getProject(), "ModuleReference_ReferencedModule.xq",
                GlobalSearchScope.getScopeRestrictedByFileTypes(GlobalSearchScope.allScope(myFixture.getProject()),
                        XQueryFileType
                                .INSTANCE));

        myFixture.renameElement(files[0], "ModuleReference_RenamedFile.xq");
        myFixture.checkResultByFile("ModuleReference.xq", "ModuleReferenceAfterRenameOfReferencedFile.xq", false);
        PsiFile[] filesAfterRename = FilenameIndex.getFilesByName(myFixture.getProject(),
                "ModuleReference_RenamedFile.xq",
                GlobalSearchScope.getScopeRestrictedByFileTypes(GlobalSearchScope.allScope(myFixture.getProject()),
                        XQueryFileType
                                .INSTANCE));
        assertEquals(1, files.length);
        assertNotNull(filesAfterRename[0]);
    }

    public void testModuleReferenceToNotExistingFile() {
        myFixture.configureByFiles("ModuleReference.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryModuleImportPath.class);

        assertNull(resolvedReference);
    }

    public void testModuleReferenceToFileWhenTwoFilesWithTheSameNameAndDifferentDirectory() {
        myFixture.configureByFiles("ModuleReference.xq", "ModuleReference_ReferencedModule.xq",
                "sub/ModuleReference_ReferencedModule.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryModuleImportPath.class);

        assertChildOf(resolvedReference, XQueryFile.class);
        assertEquals("ModuleReference_ReferencedModule.xq", ((XQueryFile) resolvedReference).getName());
        assertFalse("sub".equals(((XQueryFile) resolvedReference).getContainingDirectory().getName()));
    }

    public void testModuleReferenceToFileInDirectoryWithHyphen() {
        myFixture.configureByFiles("ModuleReferenceWithHyphen.xq", "sub-dir/ModuleReferenceWithHyphen_ReferencedModule.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryModuleImportPath.class);

        assertChildOf(resolvedReference, XQueryFile.class);
        assertEquals("ModuleReferenceWithHyphen_ReferencedModule.xq", ((XQueryFile) resolvedReference).getName());
        assertTrue("sub-dir".equals(((XQueryFile) resolvedReference).getContainingDirectory().getName()));
    }

    public void testModuleReferenceToFileInDirectoryWithHyphenInSameDirectory() {
        myFixture.configureByFiles("sub-dir/ModuleReferenceWithHyphenInSameDirectory.xq", "sub-dir/ModuleReferenceWithHyphen_ReferencedModule.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryModuleImportPath.class);

        assertChildOf(resolvedReference, XQueryFile.class);
        assertEquals("ModuleReferenceWithHyphen_ReferencedModule.xq", ((XQueryFile) resolvedReference).getName());
        assertTrue("sub-dir".equals(((XQueryFile) resolvedReference).getContainingDirectory().getName()));
    }

    public void testModuleReferenceWithRelativePath() {
        myFixture.configureByFiles("sub/ModuleReference_InSubDir.xq", "ModuleReference.xq");

        PsiElement resolvedReference = getTargetOfReferenceAtCaret(myFixture, XQueryModuleImportPath.class);

        assertChildOf(resolvedReference, XQueryFile.class);
        assertEquals("ModuleReference.xq", ((XQueryFile) resolvedReference).getName());
    }
}
