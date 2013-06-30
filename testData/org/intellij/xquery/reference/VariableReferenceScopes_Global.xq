declare variable $arg := "argGlobal";
declare variable $another := $arg;


declare function example($arg) {
    let $another := $arg
    let $arg := "argLocal"
    return $arg
};

declare function secondOne($arg) {
    $arg
};

$ar<caret>g