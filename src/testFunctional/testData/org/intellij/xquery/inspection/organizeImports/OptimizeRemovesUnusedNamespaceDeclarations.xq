module namespace local = "local";

import module "used" at "file2.xq";
declare default function namespace "used";
declare namespace html = "html";

declare function local:testOptimizeRemovesUnusedImport() {
    test()
};
