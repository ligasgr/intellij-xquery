let $result as object-node()? :=
    object-node {
        "status": "success",
        "operation": object-node {
            "name": "object-node-test",
            "retries": 2
        }
    }
return (
    $result/object-node("operation"),
    $result/object-node()
)[1]