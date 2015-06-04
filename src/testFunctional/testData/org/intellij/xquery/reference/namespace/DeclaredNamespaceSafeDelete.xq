module namespace xxx = "xxx";
import module namespace yyy = "yyy" at "yyy";
import module "aaa" at "aaa";
declare namespace z<caret>zz = "zzz";

declare function xxx:xxx() {
    $xxx:any
};