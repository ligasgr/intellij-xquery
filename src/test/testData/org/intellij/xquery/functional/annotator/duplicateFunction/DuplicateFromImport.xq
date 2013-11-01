(:import module namespace imported_namespace = 'xyz' at 'src/test/testData/org/intellij/xquery/annotator/duplicateFunction/ImportedModule.xq';:)
import module namespace imported_namespace = 'xyz' at 'ImportedModule.xq';

declare namespace duplicated_namespace = 'xyz';
<error descr="'test' is already defined in 'ImportedModule.xq'">declare function duplicated_namespace:test()</error> {
    ()
};

duplicated_namespace:test()