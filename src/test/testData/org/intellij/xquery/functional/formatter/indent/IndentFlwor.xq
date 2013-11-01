for $b in doc("http://bstore1.example.com/bib.xml")//book,
$t in $b/title,
$a in $b/author
let
$e := $a
where
exists($e)
order by
$t ascending, $a descending
return
<book>
{$t}
{$e}
</book>