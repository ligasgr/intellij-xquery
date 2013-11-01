declare variable $arg := "argGlobal";
declare variable $another := $arg;


declare function example($arg) {
    let $another := $arg
    let $arg := "argLocal"
    return $ar<caret>g
};

declare function secondOne($arg) {
    $arg
};

$arg