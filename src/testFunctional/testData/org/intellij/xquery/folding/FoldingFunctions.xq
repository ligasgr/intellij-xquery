xquery version "3.0";

declare function publicFunction() <fold text='{...}'>{
    fn:true()
}</fold>;

declare function incorrectly namedFunction() {

};

declare %private function privateFunction() <fold text='{...}'>{
    "private function"
}</fold>;

(publicFunction(), privateFunction())