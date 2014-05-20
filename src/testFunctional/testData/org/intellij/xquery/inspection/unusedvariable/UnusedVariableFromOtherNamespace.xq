declare namespace z = 'zzz';

declare function local:functionWithVariableFromOtherNamespace() {
    let $local:x := 'z'
    let $<warning descr="Variable 'z:x' is never used">z:x</warning> := 'z'
    return $local:x
};

local:functionWithVariableFromOtherNamespace()