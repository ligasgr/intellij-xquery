module namespace xxx = "xxx";
import module namespace yyy = "yyy" at "yyy";
declare namespace zzz = "zzz";

declare function xxx() {
    <a>
        <a<caret>aa:any xmlns:aaa="ccc"/>
    </a>
};