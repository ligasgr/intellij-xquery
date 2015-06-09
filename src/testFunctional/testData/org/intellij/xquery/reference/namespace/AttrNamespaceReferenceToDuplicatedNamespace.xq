module namespace xxx = "xxx";
import module namespace xxx = "yyy" at "yyy";
declare namespace zzz = "zzz";

declare function xxx() {
    <xxx:any x<caret>xx:any="val"/>
};