declare variable $arg := "argGlobal";
declare variable $another := $ar<caret>g;


declare function example($arg) {
    let $another := $arg
    let $arg := "argLocal"
    return $arg
};

declare function secondOne($arg) {
    $arg
};

$arg