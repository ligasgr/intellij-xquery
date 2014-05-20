module namespace local = "local";

import module "firstUnused" at "file1.xq";
import module "used" at "file2.xq";
import module "secondUnused" at "file3.xq";
declare default function namespace "used";

declare function local:testOptimizeRemovesUnusedImport() {
    test()
};
