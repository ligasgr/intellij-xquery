declare variable $local:var := "value";
declare function local:test() {
  $local:var
};

local:te<caret>st(), local:test(), $local:var