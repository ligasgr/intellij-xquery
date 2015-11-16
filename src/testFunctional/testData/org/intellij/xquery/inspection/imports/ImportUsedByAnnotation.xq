module namespace local = "local";

import module namespace used = "used" at "file1.xq";
<warning descr="Unused import">import module "unused" at "file2.xq";</warning>
import module namespace used_by_annotation = "used_by_annotation" at "file3.xq";

declare %used_by_annotation:annotation function local:testImportWithPrefix() {
    used:test()
};