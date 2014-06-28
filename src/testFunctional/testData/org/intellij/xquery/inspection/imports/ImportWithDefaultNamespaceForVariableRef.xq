module namespace local = "local";

<warning descr="Unused import">import module "should_not_be_used" at "file1.xq";</warning>
<warning descr="Unused import">import module "unused" at "file2.xq";</warning>
declare default function namespace "should_not_be_used";

declare function local:testDefaultNamespace() {
    ()
};