module namespace local = "local";

<warning descr="Unused import">import module "unused" at "file1.xq";</warning>

declare function local:testImportWithoutPrefix() {
    ()
};