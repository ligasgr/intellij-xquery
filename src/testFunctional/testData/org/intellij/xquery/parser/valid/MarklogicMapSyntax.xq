declare namespace apidoc="http://marklogic.com/xdmp/apidoc";

declare variable $functions := fn:doc ("/apidocs/ml-functions.xml")/node();

declare variable $namespace-names as map:map := map:map (
	<map:map xmlns:map="http://marklogic.com/xdmp/map">
		<map:entry><map:key>xdmp</map:key> <map:value xsi:type="xs:string"></map:value></map:entry>
	</map:map>
);

declare variable $categories as map:map := map:map();

let $_ := $functions/function/map:put ($categories, ./@lib/fn:string(), ./@category/fn:string())

(: TODO: Need to add namespace names to categories and tag the ones that are in scope by default :)

return
<function-categories>{
	for $key in map:keys ($categories)
	let $desc := fn:replace (map:get ($categories, $key), "Builtins", " Builtins")
	order by $key
	return <category prefix="{$key}" desc="{$desc}"/>
}</function-categories>