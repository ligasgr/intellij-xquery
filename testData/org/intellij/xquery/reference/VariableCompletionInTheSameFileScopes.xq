module namespace scope = "myscope";
declare variable $scope:globalScopeVar := .;
declare variable $globalScopeVar := .;

declare function example($functionArgumentScopeVar, $anotherOne as xs:integer) {
    for $p in $functionArgumentScopeVar
    order by $p/sales descending
    count $rank
    where $rank <= 3
    let $someVar := $anotherOne
    let $newOne := $<caret>
    return
       <product rank="{$rank}">
          {$p/name, $p/sales}
       </product>
};

declare function secondOne($products) {
    for $p in $products
    order by $p/sales descending
    count $rank
    where $rank <= 3
    return
       <product rank="{$rank}">
          {$p/name, $p/sales}
       </product>
};