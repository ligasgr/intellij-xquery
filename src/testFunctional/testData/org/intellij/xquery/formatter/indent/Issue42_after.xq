xquery version "3.0";

let $test as xs:string := "test"
return (
    fn:concat(
            "this ",
            "is ",
            "a ",
            $test
    )
)
,
"test"
,
"case"
,
"for"
,
"you"