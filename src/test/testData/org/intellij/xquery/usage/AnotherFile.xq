module namespace local = "local";

declare variable $local:var := "value";
declare function local:test() {
  $local:var
};