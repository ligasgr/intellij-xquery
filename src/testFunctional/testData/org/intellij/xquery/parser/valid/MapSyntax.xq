declare variable $z as map(*) := map {
"a" : "b",
"b" : map {"z" : map{"1" : map{}}}
};
declare variable $y as map(xs:string, map(*)) := map {'a' : map {}};
map{a:b:c},
$z('b')('z')?("1"),
$z?*[?z=map{"1" : map{}}],
map { "first" : "Jenna", "last" : "Scott" }?first,
(map {"first": "Tom"}, map {"first": "Dick"}, map {"first": "Harry"})?first