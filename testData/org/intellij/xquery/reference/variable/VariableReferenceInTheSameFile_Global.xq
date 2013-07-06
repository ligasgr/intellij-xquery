declare variable $globalScopeVar := .;

declare function example($functionArgumentScopeVar) {
    let $flworScopeVar := "anything"
    return $globalScope<caret>Var
};

"xx"