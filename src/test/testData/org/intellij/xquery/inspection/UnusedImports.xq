module namespace local = "local";

import module "namespace" at "file.xq";
import module namespace ns1 = "namespace2" at "file2.xq";
<warning descr="Unused import">import module namespace ns2 = "namespace3" at "file3.xq";</warning>
import module "namespace4" at "file4.xq";
import module namespace ns5 = "namespace5" at "file5.xq";
import module namespace ns6 = "namespace6" at "file6.xq";
<warning descr="Unused import">import module "namespace7" at "file7.xq";</warning>

declare namespace ns4 = "namespace4";
declare namespace ns7 = "namespace7";

declare default function namespace "namespace";

declare function local:testDefaultNamespace() {
    test()
};

declare function local:testImportWithPrefix() {
    ns1:test()
};

declare function local:testImportWithoutPrefix() {
    ns4:test()
};

declare function local:testVariable() {
    $ns5:variable
};

declare function local:testFunctionReference() {
    ns6:foo#2
};
