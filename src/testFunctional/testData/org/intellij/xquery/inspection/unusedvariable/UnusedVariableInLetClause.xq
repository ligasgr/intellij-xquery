module namespace local = "local";


declare function local:testImportWithoutPrefix() {
  let $value1 := fn:true()
  let <warning descr="Unused variable">$valueUnused := fn:false()</warning>
  let $value2 := fn:false()

  return $value1 or $value2
};