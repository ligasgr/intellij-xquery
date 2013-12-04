declare variable $z as map(*) := map {
    "a" := "b",
    "b" := map {"z" := map{"1" := map{},}}
};

$z("a")