let $results as array-node() :=
    array-node {
        "1. Success",
        "2. Failure"
    }
let $wrapper as object-node() := object-node { "results": $results }
return (
    $wrapper/array-node("results"),
    $wrapper/array-node()
)[1]