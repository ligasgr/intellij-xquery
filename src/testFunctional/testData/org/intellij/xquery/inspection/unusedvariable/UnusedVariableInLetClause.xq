module namespace local = "local";


declare function local:testVariablesUsedInReturnAndOneVariableNotUsed() {
  let $value1 := fn:true()
  let $<warning descr="Variable 'valueUnused' is never used">valueUnused</warning> := fn:false()
  let $value2 := fn:false()

  return $value1 or $value2
};