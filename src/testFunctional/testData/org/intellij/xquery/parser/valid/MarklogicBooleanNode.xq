let $result as object-node()? :=
    object-node {
        "completed": fn:true()
    }
return (
    $result/boolean-node("completed"),
    $result/boolean-node()
)[1]