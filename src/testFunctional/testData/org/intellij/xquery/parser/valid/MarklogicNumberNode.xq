let $result as object-node()? :=
    object-node {
        "retries": 2
    }
return (
    $result/number-node("retries"),
    $result/number-node()
)[1]