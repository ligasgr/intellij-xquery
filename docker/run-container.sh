#!/bin/sh

docker run -d -p 8000-8002:8000-8002 -p 9000-9099:9000-9099 -v /Users/ron/Work/overstory-dev/test-xquery-project/src:/opt/appserver-root overstory/general:centos-7-marklogic-9
