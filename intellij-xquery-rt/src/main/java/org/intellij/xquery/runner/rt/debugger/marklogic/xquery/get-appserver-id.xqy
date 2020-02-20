xquery version "1.0-ml";

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
: Date: 5/28/17
: Time: 6:29 PM
: To change this template use File | Settings | File Templates.
:)

declare namespace ss="http://marklogic.com/xdmp/status/server";

declare variable $appserver external;

declare function local:appserver-by-id ($appserver)
{
	let $sstats :=
		<ss:stats>{
			xdmp:server-status(xdmp:host(), xdmp:servers())
		}</ss:stats>

	let $id as xs:unsignedLong? := if ($appserver castable as xs:unsignedLong) then xs:unsignedLong ($appserver) else ()
	let $name as xs:string? := if ($appserver instance of xs:string) then $appserver else ()

	return (
		$sstats/ss:server-status[ss:port = $id]/ss:server-id/xs:unsignedLong(.),
		$sstats/ss:server-status[ss:server-id = $id]/ss:server-id/xs:unsignedLong(.),
		$sstats/ss:server-status[ss:server-name = $name]/ss:server-id/xs:unsignedLong(.)
	)[1]
};

local:appserver-by-id ($appserver)

