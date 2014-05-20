module namespace local = "local";


declare function local:testOneVariableUsedInXPathOtherNotUsed($input) {
  let $paramVal := 'abc'
  let $<warning descr="Unused variable">valueUnused</warning> := fn:false()
  let $bar := $input/foo/bar[param = $paramVal]

  return ($bar)
};