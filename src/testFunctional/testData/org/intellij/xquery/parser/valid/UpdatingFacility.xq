for $node in $root//abc:*
let $localName := fn:local-name($node),
    $newQName := fn:concat("xyz:", $localName)
return (
    rename node $node as fn:QName("http://xyz/ns", $newQName),
    for $attr in $node/@abc:*
    let $attrLocalName := fn:local-name($attr),
        $attrNewQName := fn:concat("xyz:", $attrLocalName)
    return
        rename node $attr as fn:QName("http://xyz/ns", $attrNewQName)
),

let $oldx := /a/b/x
return
    copy $newx := $oldx
    modify (rename node $newx as "newx",
    replace value of node $newx with $newx * 2)
    return ($oldx, $newx),
delete nodes /email/message[fn:currentDate() - date > xs:dayTimeDuration("P365D")],
insert node $new-police-report
as last into fn:doc("insurance.xml")/policies
        /policy[id = $pid]
        /driver[license = $license]
        /accident[date = $accdate]
        /police-reports,
insert node <year>2005</year>
after fn:doc("bib.xml")/books/book[1]/publisher