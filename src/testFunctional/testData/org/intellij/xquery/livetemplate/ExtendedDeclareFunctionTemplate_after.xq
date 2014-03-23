xquery version "3.0";
module namespace template = "template";

declare %private function name($param) as xs:string {
    'value'<caret>
};