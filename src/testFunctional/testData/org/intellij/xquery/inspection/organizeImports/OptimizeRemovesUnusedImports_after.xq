module namespace local = "local";

import module "used" at "file2.xq";
declare default function namespace "used";

declare function local:testOptimizeRemovesUnusedImport() {
    test()
};
