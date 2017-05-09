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

declare variable $id external;

declare function local:find-last-line ($line as xs:integer, $expr-id as xs:unsignedLong, $uri as xs:string)
{
	try {
		if ($expr-id = dbg:line ($id, $uri, $line + 1))
		then local:find-last-line ($line + 1, $expr-id, $uri)
		else $line
	} catch ($e) {
(:xdmp:log (xdmp:quote ($e)),:)
		$line
	}
};

declare function local:enhance-frame ($frame, $expr-id as xs:unsignedLong, $uri as xs:string)
{
	element { fn:QName ($frame/fn:namespace-uri(), $frame/fn:name()) } {
		element { fn:QName ($frame/fn:namespace-uri(), "last-line") } { text { local:find-last-line (xs:integer ($frame/*:line), $expr-id, $uri) } },
		$frame/*
	}
};

declare function local:decorate-stack ($e as element())
{
	element { fn:QName ($e/fn:namespace-uri(), $e/fn:name()) } {
		$e/namespace::*,
		$e/@*,
		$e/text(),
		$e/*[fn:not (fn:local-name() = "frame")],
		$e/*[fn:local-name() = "frame"] ! local:enhance-frame (., xs:unsignedLong($e/*:expr/*:expr-id), $e/*:expr/*:uri/fn:string())
	}
};

(: FixMe: This is not confirmed to be working yet, and the debugger code is not yet making use of the <last-line> element :)
(: ToDo: This may not be very useful until some UI is added to allow selection of specific expressions on a line (currently takes last one) :)
xdmp:log (xdmp:quote (local:decorate-stack (dbg:stack ($id)))),

local:decorate-stack (dbg:stack ($id))

