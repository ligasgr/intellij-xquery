module namespace local = "local";

import module "used" at "file1.xq";
<warning descr="Unused import">import module "unused" at "file2.xq";</warning>
import module "used_for_named_function_ref" at "file3.xq";
import module "used_for_var" at "file4.xq";

declare namespace used = "used";
declare namespace unused = "unused";
declare namespace used_for_named_function_ref = "used_for_named_function_ref";
declare namespace used_for_var = "used_for_var";

declare variable $used_for_var:test := ();

declare function local:testImportWithoutPrefix() {
    used:test(),used_for_named_function_ref:test#0,$used_for_var:test
};