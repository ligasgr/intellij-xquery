module namespace local = "local";

import module "used" at "file1.xq";
<warning descr="Unused import">import module "unused" at "file2.xq";</warning>
declare default function namespace "used";

declare function local:testDefaultNamespace() {
    test#0
};