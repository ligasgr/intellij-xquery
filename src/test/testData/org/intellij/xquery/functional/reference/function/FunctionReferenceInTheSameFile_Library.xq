module namespace name = "my namespace";

declare function name:example($functionArgumentScopeVar) {
    "x"
};

declare function name:referencing() {
  let $flworScopeVar := "anything"
  return name:ex<caret>ample($flworScopeVar)
};