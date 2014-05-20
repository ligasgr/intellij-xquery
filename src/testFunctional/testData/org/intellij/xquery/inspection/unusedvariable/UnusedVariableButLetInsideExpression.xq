module namespace local = "local";


declare function local:testVariableUsedInLetExpressionAndOtherVariableNotUsed() {
    let $value1:= fn:true()
    let $<warning descr="Variable 'valueUnused' is never used">valueUnused</warning> := fn:false() and $value1
    let $value2 := $value1 and fn:true()
    let $value3 := fn:false()

    return $value2 or $value3
};