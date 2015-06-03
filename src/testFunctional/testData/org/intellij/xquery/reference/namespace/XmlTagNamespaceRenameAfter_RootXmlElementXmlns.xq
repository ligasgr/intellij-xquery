module namespace xxx = "xxx";
import module namespace yyy = "yyy" at "yyy";
declare namespace zzz = "zzz";

declare function xxx() {
    <a xmlns:aaa="ccc">
        <a<caret>aa:any/>
    </a>
};