module namespace xxx = "xxx";
import module namespace yyy = "yyy" at "yyy";
declare namespace zzz = "zzz";

declare function xxx:xxx() {
    <yyy:any yy<caret>y:any="val"/>
};