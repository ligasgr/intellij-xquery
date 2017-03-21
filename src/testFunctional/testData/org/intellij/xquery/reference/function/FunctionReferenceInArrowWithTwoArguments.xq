declare function local:abc($string) {
    $string
};
declare function local:abc($string, $abc) {
    fn:concat($string, $abc)
};

"abc def" => upper-case() => normalize-unicode() => tokenize("\s+") => local:abc() => local:a<caret>bc("abc")