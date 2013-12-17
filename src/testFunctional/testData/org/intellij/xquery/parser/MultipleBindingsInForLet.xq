for $var1 as xs:string in (1 to 10), $var2 in (5 to 10)
let $var3 as xs:int := 1, $var4 := 2
order by $var3, $var4
group by $var5 := 'a', $var6 := 'b'
return $var2