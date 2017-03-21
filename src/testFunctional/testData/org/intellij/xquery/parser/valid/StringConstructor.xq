declare function local:prize-message($a) as xs:string {
``[Hello `{$a?name}`
You have just won `{$a?value}` dollars!
`{
if ($a?in_ca)
then ``[Well, `{$a?taxed_value}` dollars, after taxes.]``
else ""
}`]``
};

let $i := 1
let $j := 2
let $a := map {
"name": "Chris",
"value": 10000,
"taxed_value": 10000 - (10000 * 0.4),
"in_ca": true
}
return ``[`{ $i, ``[literal text]``, $j, ``[more literal text]`` }`]``, local:prize-message($a)