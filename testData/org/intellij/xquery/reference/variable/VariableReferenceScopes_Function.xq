declare variable $arg := "argGlobal";
declare variable $another := $arg;


declare function example($arg) {
    let $another := $ar<caret>g
    let $arg := "argLocal"
    return $arg
};

declare function secondOne($arg) {
    $arg
};

$arg