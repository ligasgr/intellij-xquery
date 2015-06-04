module namespace xxx = "xxx";
import module namespace y<caret>yy = "yyy" at "yyy";
import module "aaa" at "aaa";
declare namespace zzz = "zzz";

declare function zzz:xxx() {
    $zzz:any
};