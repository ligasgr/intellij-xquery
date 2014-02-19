module namespace local = "local";


declare function local:testImportWithoutPrefix($input) {
  let $paramVal := 'abc'
  let <warning descr="Unused variable">$valueUnused := fn:false()</warning>
  let $bar := $input/foo/bar[param = $paramVal]

  return ($bar)
};