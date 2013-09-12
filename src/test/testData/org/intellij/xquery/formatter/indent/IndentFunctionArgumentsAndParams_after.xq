declare function example
(
        $param1 as xs:integer*,
        $param2 as xs:integer
)
as xs:integer* {"value"};

example
(
        1,
        2
)