module namespace xxx = "xxx";
import module namespace yyy = "yyy" at "yyy";
declare namespace zzz = "zzz";

declare function xxx() {
    <a>
        <zzz:any c<caret>cc:any="val" xmlns:ccc="ccc"/>
    </a>
};