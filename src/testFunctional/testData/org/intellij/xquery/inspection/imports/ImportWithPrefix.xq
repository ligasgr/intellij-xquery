module namespace local = "local";

import module namespace used = "used" at "file1.xq";
<warning descr="Unused import">import module namespace unused = "unused" at "file2.xq";</warning>
import module namespace used_for_named_function_ref = "used_for_named_function_ref" at "file3.xq";
import module namespace used_for_var = "used_for_var" at "file4.xq";

declare variable $used_for_var:test := ();

declare function local:testImportWithPrefix() {
    used:test(),used_for_named_function_ref:test#0,$used_for_var:test
};