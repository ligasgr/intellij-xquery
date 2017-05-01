(: Copyright 2017 OverStory Ltd <copyright@overstory.co.uk> and other contributors
* (see the CONTRIBUTORS file).
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
:)

(:~
: User: ron
: Date: 4/23/17
: Time: 3:22 PM
:)

xquery version '1.0-ml';

declare namespace d="http://marklogic.com/xdmp/debug";

declare variable $id external;
declare variable $function external;

declare variable $stack := dbg:stack ($id);
declare variable $uri := xs:anyURI ($stack/d:expr/d:uri/fn:string());

declare function local:expr-by-name() as xs:unsignedLong?
{
	try {
		let $functionQName := xs:QName ($function)
		let $expr := dbg:function ($id, $uri, $functionQName)
		return $expr
	} catch ($e) {
		()
	}
};

declare function local:expr-by-stack() as xs:unsignedLong?
{
	try {
		let $line := xs:unsignedLong ($stack/d:frame[1]/d:line/fn:string())
		let $expr := dbg:line ($id, $uri, $line)[1]
		return $expr
	} catch ($e) {
		()
	}

};

(: Cannot call dbg:function for eval'ed code, because empty string does not work
   as a URI (which the effective URI of eval'ed code string).  So we try to use
   dbg:function if we can, otherwise return the first expression id on the line,
   which should be the enclosing function body.
:)
declare function local:function-expr() as xs:unsignedLong?
{
	(local:expr-by-name(), local:expr-by-stack())[1]
};


declare function local:run-to-end ($target as xs:unsignedLong)
{
	let $_ := dbg:continue ($id)
	let $_ := dbg:wait ($id, 15)
	let $req-stat := dbg:status ($id)

	return
	if ($req-stat/d:request/d:where-stopped = 'end')
	then
		if ($req-stat/d:request/d:expr-id = $target)
		then dbg:out ($id)
		else local:run-to-end (xs:integer ($target))
	else ()
};

declare function local:run-out-function ($expr as xs:unsignedLong)
{
	let $had-bp as xs:boolean := $expr = dbg:breakpoints ($id)
	let $_ := if ($had-bp) then () else dbg:break ($id, $expr)
	let $_ := local:run-to-end ($expr)
	return if ($had-bp) then dbg:clear ($id, $expr) else ()

};

let $function-expr := local:function-expr()
return
	if ($function-expr)
	then local:run-out-function ($function-expr)
	else dbg:out ($id)
