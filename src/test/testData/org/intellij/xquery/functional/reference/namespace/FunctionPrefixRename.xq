module namespace xxx = "xxx";
import module namespace yyy = "yyy" at "yyy";
declare namespace zzz = "zzz";

declare function x<caret>xx:xxx() {
    $zzz:any
};