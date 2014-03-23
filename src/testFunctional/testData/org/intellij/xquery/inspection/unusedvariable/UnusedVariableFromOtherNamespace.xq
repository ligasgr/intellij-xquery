declare namespace z = 'zzz';

declare function local:functionWithVariableFromOtherNamespace() {
    let $local:x := 'z'
    let <warning descr="Unused variable">$z:x := 'z'</warning>
    return $local:x
};

local:functionWithVariableFromOtherNamespace()