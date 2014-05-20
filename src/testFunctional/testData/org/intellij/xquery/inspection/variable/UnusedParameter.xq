module namespace local = "local";


declare function local:testFunction($param1, $<warning descr="Parameter 'param2' is never used">param2</warning> as xs:string) {
  $param1
};