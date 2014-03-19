module namespace local = "local";

import module "used" at "file1.xq";
<caret>import module "unused" at "file2.xq";
declare default function namespace "used";

declare function local:testUnusedImport() {
    test()
};