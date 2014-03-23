xquery version "3.0";

for $x in 1 to 10
group by $x
return $x