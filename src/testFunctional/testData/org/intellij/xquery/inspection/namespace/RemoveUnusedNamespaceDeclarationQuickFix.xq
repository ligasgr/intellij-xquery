module namespace local = "local";

import module "used" at "file1.xq";
<caret>declare namespace html = "http://www.w3.org/1999/xhtml";
declare default function namespace "used";

declare function local:testUnusedImport() {
    test()
};