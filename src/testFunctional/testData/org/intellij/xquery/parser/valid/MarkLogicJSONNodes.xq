let $obj as object-node() :=
    object-node {
        "status": "success",
        "operation": object-node {
            "name": "object-node-test",
            "description": "a test",
            "finished": fn:true(),
            "retries": 2,
            "coordinator": null-node { }
       }
  }
return (
  $obj/operation/node(),
  $obj/operation/node("bob"),
  $obj/operation/text(),
  $obj/operation/text("name"),
  $obj/operation/number-node()
)
