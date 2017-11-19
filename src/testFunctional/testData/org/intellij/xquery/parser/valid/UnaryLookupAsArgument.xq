xquery version "3.0";
let $f := fn:string-join(?test, ', ')
return $f