module namespace local = "local";


declare function local:testVariableUsedInLetExpressionAndOtherVariableNotUsed() {
    let $value1:= fn:true()
    let <warning descr="Unused variable">$valueUnused := fn:false() and $value1</warning>
    let $value2 := $value1 and fn:true()
    let $value3 := fn:false()

    return $value2 or $value3
};