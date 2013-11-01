declare variable $z as map(*) := map {
    "a" := "b",
    "b" := map {"z" := map{}}
};

$z("a")