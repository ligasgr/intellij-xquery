declare variable $local:a := 'a';
declare %private variable $<warning descr="Variable 'local:b' is never used">local:b</warning> := 'b';
declare %private variable $local:c := 'c';

$local:c