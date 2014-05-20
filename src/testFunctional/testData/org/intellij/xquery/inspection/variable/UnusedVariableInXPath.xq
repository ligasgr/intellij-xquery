module namespace local = "local";


declare function local:testOneVariableUsedInXPathOtherNotUsed($input) {
  let $paramVal := 'abc'
  let $<warning descr="Variable 'valueUnused' is never used">valueUnused</warning> := fn:false()
  let $bar := $input/foo/bar[param = $paramVal]

  return ($bar)
};