module namespace xxx = "xxx";
import module namespace yyy = "yyy" at "yyy";
declare namespace zzz = "zzz";

declare function xxx:xxx() {
    <zzz:any zz<caret>z:any="val"/>
};