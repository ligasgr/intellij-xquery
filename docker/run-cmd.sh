#!/bin/sh

sh /etc/init.d/MarkLogic start

while [ true ] ; do
	sleep 1000
done
