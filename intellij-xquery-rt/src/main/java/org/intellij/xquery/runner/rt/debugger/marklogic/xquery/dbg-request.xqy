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
: Time: 3:09 PM
:)

xquery version '1.0-ml';

declare namespace d = 'http://marklogic.com/xdmp/debug';

declare variable $mode external;
declare variable $root external;
declare variable $argument external;
declare variable $variables external;
declare variable $connect-timeout external;
(: ToDo: handle options :)

declare variable $interval := 3;	(: number of seconds to sleep between polls awaiting request start on a connected appserver :)

declare function local:gen-vars() as item()* {
	for $var in xdmp:unquote ($variables)/variables/variable
	let $qname := fn:QName ($var/@ns/fn:data(), $var/@name)
	return ($qname, $var/fn:data())
};

declare variable $vars := local:gen-vars();

declare function local:clear-connected ($appserver-id)
{
	if ($appserver-id = dbg:connected())
	then dbg:disconnect ($appserver-id)
	else ()
};

declare function local:await-request ($appserver-id as xs:unsignedLong, $remaining-tries as xs:integer)
{
	let $request-id := dbg:stopped ($appserver-id)[1]
	let $timeout-qname := xs:QName("dbg:await-request-timeout")
	return
	if ($request-id)
	then $request-id
	else
		if ($remaining-tries > 0)
		then (xdmp:sleep ($interval * 1000), local:await-request ($appserver-id, $remaining-tries - 1))
		else fn:error ($timeout-qname, "Timeout awaiting request start (" || $connect-timeout || " seconds) on appserver '" || xdmp:server-name ($appserver-id) || "' in mode '"  || $mode || "'", $timeout-qname)
};

declare function local:connect-appserver ($appserver)
{
	if ($appserver)
	then (local:clear-connected ($appserver), dbg:connect ($appserver), local:await-request ($appserver, $connect-timeout idiv $interval))
	else fn:error (xs:QName("dbg:no-such-appserver"), "Cannot find given appserver: " || ($appserver, $appserver)[1])
};

let $id :=
	switch (fn:lower-case ($mode))
	case 'adhoc' return dbg:eval ($argument, $vars)
	case 'invoke' return dbg:invoke ($argument, $vars)
	case 'capture appserver' return local:connect-appserver (xs:unsignedLong ($argument))
	default return fn:error (xs:QName("dbg:bad-run-mode"), "Unexpected MarkLogic Debugger Run Mode: " || $mode)

let $status := dbg:status ($id)
(:let $_ := xdmp:log ($status):)
return
	if ($status/d:request/error:error/error:format-string/fn:string())
	then fn:error (xs:QName($status/d:request/error:error/error:name/fn:string()), $status/d:request/error:error/error:format-string/fn:string())
	else $id


