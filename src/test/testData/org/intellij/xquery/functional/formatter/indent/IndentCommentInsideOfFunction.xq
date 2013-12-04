module namespace IndentCommentInsideOfFunction = "IndentCommentInsideOfFunction";

declare function example() {
(: comment :)
''
};

declare function example2() {(:
comment :)
''
};

declare function example3() {
(:
comment
:)
''
};
