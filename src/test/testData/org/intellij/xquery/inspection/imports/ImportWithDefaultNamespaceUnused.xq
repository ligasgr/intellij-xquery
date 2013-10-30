module namespace local = "local";

<warning descr="Unused import">import module "unused" at "file1.xq";</warning>
declare default function namespace "unused";

declare function local:testDefaultNamespace() {
    ()
};