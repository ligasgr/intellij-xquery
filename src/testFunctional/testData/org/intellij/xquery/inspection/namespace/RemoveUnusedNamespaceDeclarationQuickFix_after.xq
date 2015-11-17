module namespace local = "local";

import module "used" at "file1.xq";
<caret>declare default function namespace "used";

declare function local:testUnusedImport() {
    test()
};