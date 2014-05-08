xquery version "3.0";
module namespace f = 'FoldingFunctions.xq';

declare function publicFunction() <fold text='{...}'>{
    fn:true()
}</fold>;

declare %private function privateFunction() <fold text='{...}'>{
    "private function"
}</fold>;

declare function incorrectly namedFunction() {

};