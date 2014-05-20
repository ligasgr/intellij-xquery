module namespace local = "local";


declare function local:testVariableDefinedInForClauseButNotUsed() {
    for $<warning descr="Variable 'var' is never used">var</warning> in (1, 2, 3, 4)
        where 2 < 3
        return "foo"
};

declare function local:testVariableDefinedInForThenUsedInLetWhichIsNotUsed() {
    for $var in (1, 2, 3, 4)
        let $<warning descr="Variable 'bar' is never used">bar</warning> := $var * 2
        where 2 < 3
        return "foo"
};

declare function local:testVariableDefinedInForThenUsedInWhere() {
    for $var in (1, 2, 3, 4)
        where $var < 3
        return "foo"
};

declare function local:testVariableDefinedInForThenUsedInLetWhichIsUsedInReturn() {
    for $var in (1, 2, 3, 4)
        let $bar := $var * 2
        where 2 < 3
        return <x>{$bar}</x>
};
