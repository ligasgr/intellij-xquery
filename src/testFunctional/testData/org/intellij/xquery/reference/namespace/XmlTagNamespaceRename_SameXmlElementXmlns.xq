module namespace xxx = "xxx";
import module namespace yyy = "yyy" at "yyy";
declare namespace zzz = "zzz";

declare function xxx() {
    <a>
        <c<caret>cc:any xmlns:ccc="ccc"/>
    </a>
};