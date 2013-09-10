module namespace name = "my namespace";

declare function name:example($functionArgumentScopeVar) {
    "x"
};

declare function name:referencing() {
  let $flworScopeVar := name:ex<caret>ample#1
  return $flworScopeVar(1)
};