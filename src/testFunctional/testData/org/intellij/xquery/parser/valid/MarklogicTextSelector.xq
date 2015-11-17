let $result as object-node()? :=
    object-node {
        "status": "success"
    }
return (
    $result/text("status"),
    $result/text()
)[1]