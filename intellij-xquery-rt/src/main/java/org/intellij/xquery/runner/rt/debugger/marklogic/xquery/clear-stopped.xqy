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

declare variable $captured-appserver-id as xs:unsignedLong? external;

declare variable $appserver-id := if ($captured-appserver-id) then $captured-appserver-id else xdmp:server ('TaskServer');

for $req in dbg:stopped ($appserver-id)
return (
	xdmp:request-cancel (xdmp:host(), $appserver-id, $req),
	dbg:status ($req)	(: This clears deferred error status for the canceled request :)
)
,

if ($captured-appserver-id = dbg:connected())
then dbg:disconnect ($captured-appserver-id)
else ()
