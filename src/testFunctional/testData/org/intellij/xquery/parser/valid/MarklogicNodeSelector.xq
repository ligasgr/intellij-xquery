let $result as object-node()? :=
    object-node {
        "status": "success"
    }
return (
    $result/node("status"),
    $result/node()
)[1]