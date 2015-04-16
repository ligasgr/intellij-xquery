let $result as object-node()? :=
    object-node {
        "status": "success",
        "operation": null-node {}
    }
return (
    $result/null-node("operation"),
    $result/null-node()
)[1]