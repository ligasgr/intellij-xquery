module namespace xxx = "xxx";
import module namespace xxx = "yyy" at "yyy";
declare namespace zzz = "zzz";

declare function x<caret>xx:xxx() {
    $zzz:any
};