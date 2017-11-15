xquery version "3.0";
let $func-partial := fn:string-join(', ', ?)
return $func-partial((1, 2, 3))